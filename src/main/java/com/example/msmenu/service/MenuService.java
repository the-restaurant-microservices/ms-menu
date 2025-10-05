package com.example.msmenu.service;

import com.example.msmenu.client.PaymentClient;
import com.example.msmenu.client.RestaurantClient;
import com.example.msmenu.dao.entity.MenuEntity;
import com.example.msmenu.dao.repository.MenuRepository;
import com.example.msmenu.dto.request.CreateMenuRequest;
import com.example.msmenu.dto.request.OrderRequest;
import com.example.msmenu.dto.request.PaymentRequest;
import com.example.msmenu.dto.request.UpdateMenuRequest;
import com.example.msmenu.dto.response.MenuResponse;
import com.example.msmenu.dto.response.PaymentResponse;
import com.example.msmenu.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.example.msmenu.mapper.MenuMapper.MENU_MAPPER;
import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class MenuService {

    MenuRepository menuRepository;
    RestaurantClient restaurantClient;
    PaymentClient paymentClient;

    public MenuResponse create(CreateMenuRequest request) {
        restaurantClient.getRestaurantById(request.getRestaurantId());

        MenuEntity entity = MENU_MAPPER.requestToEntity(request);
        MenuEntity saved = menuRepository.save(entity);
        return MENU_MAPPER.entityToResponse(saved);
    }

    public List<MenuResponse> findAll() {
        return menuRepository.findAll().stream()
                .map(MENU_MAPPER::entityToResponse)
                .toList();
    }

    public MenuResponse findById(Long id) {
        MenuEntity menu = menuRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Menu not found with id: " + id));
        return MENU_MAPPER.entityToResponse(menu);
    }

    public MenuResponse update(Long id, UpdateMenuRequest request) {
        MenuEntity menu = menuRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Menu not found with id: " + id));

        restaurantClient.getRestaurantById(request.getRestaurantId());

        MENU_MAPPER.updateMenu(menu, request);
        MenuEntity updated = menuRepository.save(menu);

        return MENU_MAPPER.entityToResponse(updated);
    }

    public void delete(Long id) {
        if (!menuRepository.existsById(id)) {
            throw new NotFoundException("Menu not found with id: " + id);
        }
        menuRepository.deleteById(id);
    }

    public PaymentResponse orderFood(OrderRequest orderRequest) {
        List<MenuEntity> items = menuRepository.findAllById(orderRequest.getMenuItemIds());
        if (items.isEmpty()) {
            throw new RuntimeException("Menu items not found");
        }
        BigDecimal totalPrice = items.stream()
                .map(MenuEntity::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        PaymentRequest paymentRequest = PaymentRequest.builder()
                .userId(orderRequest.getUserId())
                .amount(totalPrice)
                .build();

        return paymentClient.makePayment(paymentRequest);
    }
}
