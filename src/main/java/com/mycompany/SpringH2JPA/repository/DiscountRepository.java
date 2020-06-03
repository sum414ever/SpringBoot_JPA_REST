package com.mycompany.SpringH2JPA.repository;

import com.mycompany.SpringH2JPA.model.Discount;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

  List<Discount> findAllByItemId(Long itemId);
  Discount findByItemId(Long itemId);
}
