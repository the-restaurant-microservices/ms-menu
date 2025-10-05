package com.example.msmenu.client;

import com.example.msmenu.dto.response.RestaurantResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "restaurant-service", url = "${restaurant.service.url}")
public interface RestaurantClient {

    @GetMapping("/api/v1/restaurants/{id}")
    RestaurantResponse getRestaurantById(@PathVariable("id") Long id);
}

