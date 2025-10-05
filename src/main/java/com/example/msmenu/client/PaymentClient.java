package com.example.msmenu.client;

import com.example.msmenu.dto.request.PaymentRequest;
import com.example.msmenu.dto.response.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "paymentClient", url = "https://payment-texnoera.onrender.com/v1/payments")
public interface PaymentClient {
    @PostMapping
    PaymentResponse makePayment(@RequestBody PaymentRequest paymentRequest);
}