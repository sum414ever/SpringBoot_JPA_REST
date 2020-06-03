package com.mycompany.SpringH2JPA.service;

import com.mycompany.SpringH2JPA.enums.ItemCategory;
import com.mycompany.SpringH2JPA.model.Item;
import com.mycompany.SpringH2JPA.repository.ItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {

  @Autowired
  private ItemRepository itemRepository;

  @Override
  public Item getById(Long id) {
    return itemRepository.findById(id).orElse(new Item());
  }

  @Override
  public Item findByName(String name) {
    return itemRepository.findByName(name);
  }

  @Override
  public Item save(Item item) {

    ItemCategory itemCategory;
    try {
      itemCategory = ItemCategory.valueOf(item.getCategory().toUpperCase());
    } catch (IllegalArgumentException e) {
      itemCategory = ItemCategory.OTHER;
    }
    String category = itemCategory.toString();
    item.setCategory(category);

    itemRepository.save(item);
    return item;
  }

  @Override
  public void delete(Long id) {
    itemRepository.deleteById(id);
  }

  @Override
  public List<Item> getAll() {
    return itemRepository.findAll();
  }

  @Override
  public List<Item> findAllByCategory(String category) {
    return itemRepository.findAllByCategory(category);
  }
}
