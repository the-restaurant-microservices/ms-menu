package com.example.msmenu.mapper;

import com.example.msmenu.dao.entity.MenuEntity;
import com.example.msmenu.dto.request.CreateMenuRequest;
import com.example.msmenu.dto.response.MenuResponse;
import com.example.msmenu.dto.request.UpdateMenuRequest;
import com.example.msmenu.enums.Status;

public enum MenuMapper {

    MENU_MAPPER;

    public MenuEntity requestToEntity(CreateMenuRequest request) {
        return MenuEntity.builder()
                .status(Status.ACTIVE)
                .restaurantId(request.getRestaurantId())
                .title(request.getTitle())
                .price(request.getPrice())
                .available(request.getAvailable())
                .build();
    }

    public MenuResponse entityToResponse(MenuEntity entity) {
        return MenuResponse.builder()
                .id(entity.getId())
                .status(entity.getStatus())
                .restaurantId(entity.getRestaurantId())
                .title(entity.getTitle())
                .price(entity.getPrice())
                .available(entity.getAvailable())
                .createdAt(entity.getCreatedAt())
                .build();
    }

    public void updateMenu(MenuEntity entity, UpdateMenuRequest request) {
        entity.setStatus(Status.IN_PROGRESS);

        if (request.getRestaurantId() != null) {
            entity.setRestaurantId(request.getRestaurantId());
        }
        if (request.getTitle() != null && !request.getTitle().isEmpty()) {
            entity.setTitle(request.getTitle());
        }
        if (request.getPrice() != null) {
            entity.setPrice(request.getPrice());
        }
        if (request.getAvailable() != null) {
            entity.setAvailable(request.getAvailable());
        }
    }

}
