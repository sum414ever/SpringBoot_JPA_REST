package com.mycompany.SpringH2JPA.controller;

import com.mycompany.SpringH2JPA.model.Order;
import com.mycompany.SpringH2JPA.service.OrderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

  @Autowired
  private OrderService orderService;

  @PostMapping("/{userId}/{itemId}")
  public ResponseEntity<Order> addOrder(@PathVariable Long userId, @PathVariable Long itemId) {

    Order order = new Order(userId, itemId);
    orderService.save(order);

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(order);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Order> getById(@PathVariable Long id) {
    Order order = orderService.getById(id);

    if (order.getItemId() == 0 && order.getUserId() == 0) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(order);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable Long id) {
    orderService.delete(id);

    return ResponseEntity
        .status(HttpStatus.NO_CONTENT)
        .body("Order with id " + id + " was deleted successfully");
  }

  @GetMapping()
  public ResponseEntity<List<Order>> getAll() {
    List<Order> orders = orderService.getAll();
    if (orders.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(orders);
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<List<Order>> getAllByUserName(@PathVariable String name) {
    List<Order> orders = orderService.getAllByUserName(name);
    if (orders.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(orders);
  }
}
