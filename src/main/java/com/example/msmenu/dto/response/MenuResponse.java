package com.example.msmenu.dto.response;


import com.example.msmenu.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
public class MenuResponse {
    Long id;
    Status status;
    Long restaurantId;
    String title;
    BigDecimal price;
    Boolean available;
    LocalDateTime createdAt;
}
