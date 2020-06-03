package com.mycompany.SpringH2JPA.service;

import com.mycompany.SpringH2JPA.model.Discount;
import java.util.List;

public interface DiscountService {

  Discount getById(Long id);

  Discount getByItemId(Long itemId);

  void save(Discount discount);

  void delete(Long id);

  List<Discount> getAll();

  List<Discount> findAllByItemName(String name);
}
