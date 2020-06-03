package com.mycompany.SpringH2JPA.service;

import com.mycompany.SpringH2JPA.model.Order;
import java.util.List;

public interface OrderService {

  Order getById(Long id);

  void save(Order order);

  void delete(Long id);


  List<Order> getAll();

  List<Order> getAllByUserName(String userName);
}
