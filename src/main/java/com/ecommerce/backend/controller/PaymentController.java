package com.ecommerce.backend.controller;


import com.ecommerce.backend.exception.OrderException;
import com.ecommerce.backend.exception.PaymentException;
import com.ecommerce.backend.exception.ProductException;
import com.ecommerce.backend.exception.UserException;
import com.ecommerce.backend.model.Order;
import com.ecommerce.backend.model.Product;
import com.ecommerce.backend.repository.OrderRepository;
import com.ecommerce.backend.response.ApiResponse;
import com.ecommerce.backend.service.OrderService;
import com.ecommerce.backend.service.ProductService;
import com.ecommerce.backend.service.UserService;


import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.model.checkout.Session;

import com.stripe.param.checkout.SessionCreateParams;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/api")
public class PaymentController {

    @Value("${stripe.key.secret}")
    String stripeApiKey;
    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;
    private final OrderRepository orderRepository;

    public PaymentController(OrderService orderService, UserService userService, ProductService productService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.userService = userService;
        this.productService = productService;
        this.orderRepository = orderRepository;
    }

  @PostMapping("/payments/{orderId}")
  public ResponseEntity<PaymentLinkResponse> createPaymentLink(@PathVariable Long orderId,
                                                               @RequestHeader("Authorization") String jwt) throws UserException, OrderException, ProductException {
      Order order = orderService.findOrderById(orderId);
      List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();

      try {
          Stripe.apiKey = stripeApiKey;

          List<Product> products = productService.findProductsByOrderId(orderId);

          // Agrega cada producto como un elemento de línea en la sesión de pago
          for (Product product : products) {
              lineItems.add(SessionCreateParams.LineItem.builder()
                      .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                              .setCurrency("usd")
                              .setUnitAmount(((long)product.getPrice() * 100)) // Supongo que tienes un campo "price" en tu objeto Product
                              .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                      .setName(product.getTitle())
                                      .build())
                              .build())
                      .setQuantity(1L)
                      .build());
          }

          // Construye los parámetros para la sesión de pago
          SessionCreateParams.Builder paramsBuilder = SessionCreateParams.builder();
          paramsBuilder.addAllLineItem(lineItems); // Agrega todos los elementos de línea al constructor

          // Agrega el modo de la sesión de pago (en este caso, 'payment' para pagos únicos)
          paramsBuilder.setMode(SessionCreateParams.Mode.PAYMENT);

          // Agrega las URLs directamente a los parámetros de la sesión de pago
          // Agrega las URLs directamente a los parámetros de la sesión de pago
          paramsBuilder.setSuccessUrl("http://localhost:4200/payment-success?order_id=" + order.getId());
          paramsBuilder.setCancelUrl("http://localhost:4200/payment-cancel?order_id=" + order.getId());

// Construye los parámetros y crea la sesión de pago
          SessionCreateParams sessionParams = paramsBuilder.build();
          Session session = Session.create(sessionParams);

          PaymentLinkResponse res = new PaymentLinkResponse();
          res.setPayment_link_id(session.getId());
          res.setPayment_link_url(session.getUrl());

          return new ResponseEntity<>(res, HttpStatus.CREATED);
      } catch (StripeException e) {
          throw new OrderException("Error al crear el enlace de pago: " + e.getMessage());
      }
  }


    @GetMapping("/payments")
    public ResponseEntity<ApiResponse> updatePayment(@RequestParam(name = "payment_id", required = true) String paymentId,
                                                     @RequestParam(name = "order_id", required = true) Long orderId) throws OrderException, PaymentException {

        // Lógica para actualizar el estado del pago en la base de datos
        try {
            Order order = orderService.findOrderById(orderId);

            // Supongamos que aquí verificas en tu base de datos si el pago está completo
            // y actualizas el estado del pedido en consecuencia
            order.setOrderStatus("COMPLETED");
            orderRepository.save(order);

            ApiResponse res = new ApiResponse();
            res.setMessage("Payment status updated successfully");
            res.setStatus(true);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            throw new PaymentException(e.getMessage());
        }
    }
}