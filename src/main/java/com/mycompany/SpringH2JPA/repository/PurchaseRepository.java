package com.mycompany.SpringH2JPA.repository;

import com.mycompany.SpringH2JPA.model.Purchase;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

  List<Purchase> findAllByUserId(Long userId);
}
