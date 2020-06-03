package com.mycompany.SpringH2JPA.service;

import com.mycompany.SpringH2JPA.model.Item;
import java.util.List;

public interface ItemService {

  Item getById(Long id);

  Item findByName(String name);

  Item save(Item item);

  void delete(Long id);

  List<Item> getAll();

  List<Item> findAllByCategory(String category);

}
