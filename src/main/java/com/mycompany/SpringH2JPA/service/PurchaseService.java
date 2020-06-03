package com.mycompany.SpringH2JPA.service;

import com.mycompany.SpringH2JPA.model.Purchase;
import java.util.List;

public interface PurchaseService {

  Purchase getById(Long id);

  void save(Purchase purchase);

  void delete(Long id);

  List<Purchase> getAll();

  List<Purchase> getAllByUserName(String userName);
}
