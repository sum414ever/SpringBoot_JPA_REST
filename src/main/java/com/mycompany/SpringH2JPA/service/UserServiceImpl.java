package com.mycompany.SpringH2JPA.service;

import com.mycompany.SpringH2JPA.model.User;
import com.mycompany.SpringH2JPA.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public User getById(Long id) {
    return userRepository.findById(id).orElse(new User());
  }


  @Override
  public User getByName(String userName) {
    return userRepository.findByName(userName);
  }

  @Override
  public void save(User user) {
    userRepository.save(user);
  }

  @Override
  public void delete(Long id) {
    userRepository.deleteById(id);
  }

  @Override
  public List<User> getAll() {
    return userRepository.findAll();
  }
}
