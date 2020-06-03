package com.mycompany.SpringH2JPA.controller;

import com.mycompany.SpringH2JPA.enums.ItemCategory;
import com.mycompany.SpringH2JPA.model.Item;
import com.mycompany.SpringH2JPA.service.ItemService;
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
@RequestMapping("/items")
public class ItemController {

  @Autowired
  private ItemService itemService;

  @PostMapping()
  public ResponseEntity<Item> saveItem(@RequestBody Item item) {
    if (item.getName() == null && item.getPrice() == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(itemService.save(item));
  }

  @GetMapping("/{id}")
  public ResponseEntity<Item> getById(@PathVariable Long id) {
    Item item = itemService.getById(id);
    if (item.getName() == null && item.getPrice() == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(item);
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<Item> getByName(@PathVariable String name) {
    Item item = itemService.findByName(name);
    if (item.getName() == null && item.getPrice() == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(item);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity delete(@PathVariable Long id) {
    if (itemService.getById(id) == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    itemService.delete(id);
    return ResponseEntity
        .status(HttpStatus.NO_CONTENT)
        .body("Item with id " + id + " was deleted successfully");
  }

  @GetMapping()
  public ResponseEntity<List<Item>> getAll() {
    List<Item> items = itemService.getAll();
    if (items.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(items);
  }

  @GetMapping("/category/{category}")
  public ResponseEntity<List<Item>> getAllByCategory(@PathVariable String category) {

    ItemCategory itemCategory;
    try {
      itemCategory = ItemCategory.valueOf(category.toUpperCase());
    } catch (IllegalArgumentException e) {
      itemCategory = ItemCategory.OTHER;
    }
    category = itemCategory.toString();
    List<Item> items = itemService.findAllByCategory(category);
    if (items.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(items);
  }
}
