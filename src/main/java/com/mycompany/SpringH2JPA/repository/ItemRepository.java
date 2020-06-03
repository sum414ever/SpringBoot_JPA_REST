package com.mycompany.SpringH2JPA.repository;

import com.mycompany.SpringH2JPA.model.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

  Item findByName(String name);

  List<Item> findAllByCategory(String category);
}
