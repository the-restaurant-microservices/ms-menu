package com.example.msmenu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class PaymentResponse {
    Long id;
    Long userId;
    BigDecimal amount;
}