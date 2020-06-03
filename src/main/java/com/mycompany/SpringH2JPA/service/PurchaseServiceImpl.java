package com.mycompany.SpringH2JPA.service;

import com.mycompany.SpringH2JPA.model.Purchase;
import com.mycompany.SpringH2JPA.model.User;
import com.mycompany.SpringH2JPA.repository.PurchaseRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseServiceImpl implements PurchaseService {

  @Autowired
  private PurchaseRepository purchaseRepository;
  @Autowired
  private UserService userService;

  @Override
  public Purchase getById(Long id) {
    return purchaseRepository.getOne(id);
  }

  @Override
  public void save(Purchase purchase) {
    purchaseRepository.save(purchase);
  }

  @Override
  public void delete(Long id) {
    purchaseRepository.deleteById(id);
  }

  @Override
  public List<Purchase> getAll() {
    return purchaseRepository.findAll();
  }

  @Override
  public List<Purchase> getAllByUserName(String userName) {
    User user = userService.getByName(userName);
    return purchaseRepository.findAllByUserId(user.getId());
  }
}
