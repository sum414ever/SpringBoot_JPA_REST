package com.mycompany.SpringH2JPA.service;

import com.mycompany.SpringH2JPA.model.Order;
import com.mycompany.SpringH2JPA.model.User;
import com.mycompany.SpringH2JPA.repository.OrderRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private UserService userService;

  @Override
  public Order getById(Long id) {
    return orderRepository.findById(id).orElse(new Order());
  }

  @Override
  public void save(Order order) {
    orderRepository.save(order);
  }

  @Override
  public void delete(Long id) {
    orderRepository.deleteById(id);
  }


  @Override
  public List<Order> getAll() {
    return orderRepository.findAll();
  }

  @Override
  public List<Order> getAllByUserName(String userName) {
    User user = userService.getByName(userName);
    return orderRepository.findAllByUserId(user.getId());
  }
}
