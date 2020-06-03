package com.mycompany.SpringH2JPA.controller;

import com.mycompany.SpringH2JPA.model.User;
import com.mycompany.SpringH2JPA.service.UserService;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("/{id}")
  public ResponseEntity<User> getById(@PathVariable Long id) {
    User user = userService.getById(id);
    if (user.getName() == null && user.getBalance() == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(user);
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<User> getByName(@PathVariable String name) {
    User user = userService.getByName(name);
    if (user.getName() == null && user.getBalance() == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    return ResponseEntity
        .status(HttpStatus.OK)
        .body(user);
  }

  @PostMapping()
  public ResponseEntity<User> saveUser(@RequestBody User user) {
    if (user == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    userService.save(user);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(user);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteUser(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity
        .status(HttpStatus.NO_CONTENT)
        .body("User with id " + id + " was deleted successfully");
  }

  @GetMapping()
  public ResponseEntity<List<User>> getAll() {
    List<User> users = userService.getAll();
    if (users.isEmpty()) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return ResponseEntity
        .status(HttpStatus.OK)
        .body(users);
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> addBalance(@PathVariable Long id, @RequestBody User user) {
    if (user.getBalance() == null) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    User user1 = userService.getById(id);
    BigDecimal balance = user1.getBalance().add(user.getBalance());
    user1.setBalance(balance);
    userService.save(user1);

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(user1);
  }
}
