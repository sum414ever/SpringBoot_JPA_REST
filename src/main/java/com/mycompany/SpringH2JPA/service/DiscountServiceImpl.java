package com.mycompany.SpringH2JPA.service;

import com.mycompany.SpringH2JPA.model.Discount;
import com.mycompany.SpringH2JPA.model.Item;
import com.mycompany.SpringH2JPA.repository.DiscountRepository;
import com.mycompany.SpringH2JPA.repository.ItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiscountServiceImpl implements DiscountService {

  @Autowired
  private DiscountRepository discountRepository;
  @Autowired
  private ItemRepository itemRepository;

  @Override
  public Discount getById(Long id) {
    return discountRepository.findById(id).orElse(new Discount());
  }

  @Override
  public Discount getByItemId(Long itemId) {
    return discountRepository.findByItemId(itemId);
  }

  @Override
  public void save(Discount discount) {
    discountRepository.save(discount);
  }

  @Override
  public void delete(Long id) {
    discountRepository.deleteById(id);
  }

  @Override
  public List<Discount> getAll() {
    return discountRepository.findAll();
  }

  @Override
  public List<Discount> findAllByItemName(String name) {
    Item item = itemRepository.findByName(name);
    return discountRepository.findAllByItemId(item.getId());
  }
}
