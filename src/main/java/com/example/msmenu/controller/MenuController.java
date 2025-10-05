package com.example.msmenu.controller;

import com.example.msmenu.dto.request.CreateMenuRequest;
import com.example.msmenu.dto.request.OrderRequest;
import com.example.msmenu.dto.request.UpdateMenuRequest;
import com.example.msmenu.dto.response.MenuResponse;
import com.example.msmenu.dto.response.PaymentResponse;
import com.example.msmenu.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/v1/menus")
@FieldDefaults(level = PRIVATE,  makeFinal = true)
@RequiredArgsConstructor
public class MenuController {

    MenuService menuService;

    @PostMapping
    public ResponseEntity<MenuResponse> create(@RequestBody CreateMenuRequest request) {
        return ResponseEntity.ok(menuService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<MenuResponse>> findAll() {
        return ResponseEntity.ok(menuService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(menuService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateMenuRequest request
    ) {
        return ResponseEntity.ok(menuService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        menuService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/order")
    public PaymentResponse orderFood(@RequestBody OrderRequest orderRequest) {
        return menuService.orderFood(orderRequest);
    }
}
