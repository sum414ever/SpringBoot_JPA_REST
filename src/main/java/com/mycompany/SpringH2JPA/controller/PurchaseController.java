package com.mycompany.SpringH2JPA.controller;

import com.mycompany.SpringH2JPA.model.Purchase;
import com.mycompany.SpringH2JPA.service.PurchaseService;
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
@RequestMapping("/purchases")
public class PurchaseController {

  @Autowired
  private PurchaseService purchaseService;

  @PostMapping("/{userId}/{itemId}")
  public ResponseEntity<Purchase> addOrder(@PathVariable Long userId, @PathVariable Long itemId) {

    Purchase purchase = new Purchase(userId, itemId);
    purchaseService.save(purchase);

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(purchase);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Purchase> getById(@PathVariable Long id) {
    Purchase purchase = purchaseService.getById(id);

    if (purchase.getItemId() == 0 && purchase.getUserId() == 0) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(purchase);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable Long id) {
    purchaseService.delete(id);

    return ResponseEntity
        .status(HttpStatus.NO_CONTENT)
        .body("Purchase with id " + id + " was deleted successfully");
  }

  @GetMapping()
  public ResponseEntity<List<Purchase>> getAll() {
    List<Purchase> purchases = purchaseService.getAll();
    if (purchases.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(purchases);
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<List<Purchase>> getAllByUserName(@PathVariable String name) {
    List<Purchase> purchases = purchaseService.getAllByUserName(name);
    if (purchases.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(purchases);
  }
}
