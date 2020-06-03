package com.mycompany.SpringH2JPA.service;

import com.mycompany.SpringH2JPA.model.User;
import java.util.List;

public interface UserService {

  User getById(Long id);

  User getByName(String userName);

  void save(User user);

  void delete(Long id);

  List<User> getAll();
}
