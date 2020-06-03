package com.mycompany.SpringH2JPA.controller;

import com.mycompany.SpringH2JPA.service.PayService;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pay")
public class PayController {

  @Autowired
  private PayService payService;

  @GetMapping("/{userId}/{itemId}")
  public ResponseEntity payOne(@PathVariable Long userId, @PathVariable Long itemId) {

    BigDecimal itemCost = payService.payOne(userId, itemId);

    if (itemCost == null) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("User with id " + userId + " haven't enough money");
    } else {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body("User with id " + userId + " bought item with id " + itemId + " for " + itemCost
              + " money");
    }
  }

  @GetMapping("/{userId}")
  public ResponseEntity payAll(@PathVariable Long userId) {
    BigDecimal itemsCost = payService.payAll(userId);
    if (itemsCost == null) {
      return ResponseEntity
          .status(HttpStatus.BAD_REQUEST)
          .body("User with id " + userId + " haven't enough money");
    } else {
      return ResponseEntity
          .status(HttpStatus.OK)
          .body("User with id " + userId + " bought all the items  for " + itemsCost
              + " money");
    }
  }
}
