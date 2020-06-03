package com.mycompany.SpringH2JPA.controller;

import com.mycompany.SpringH2JPA.model.Discount;
import com.mycompany.SpringH2JPA.service.DiscountService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/discounts")
public class DiscountController {

  @Autowired
  private DiscountService discountService;

  @PostMapping()
  public ResponseEntity<Discount> save(@RequestBody Discount discount) {
    if (discount.getItemId() == 0 & discount.getDiscount() == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    discountService.save(discount);
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(discount);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Discount> getById(@PathVariable Long id) {
    Discount discount = discountService.getById(id);
    if (discount.getItemId() == 0 & discount.getDiscount() == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(discount);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable Long id) {

    discountService.delete(id);
    return ResponseEntity
        .status(HttpStatus.NO_CONTENT)
        .body("Discount with id " + id + " was deleted successfully");
  }

  @GetMapping()
  public ResponseEntity<List<Discount>> getAll() {
    List<Discount> discounts = discountService.getAll();
    if (discounts.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(discounts);
  }

  @GetMapping("/name/{itemName}")
  public ResponseEntity<List<Discount>> findAllByItemName(@PathVariable String itemName) {
    List<Discount> discounts = discountService.findAllByItemName(itemName);

    if (discounts.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(discounts);
  }
}
