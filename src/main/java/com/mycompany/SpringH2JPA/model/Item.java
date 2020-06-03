package com.mycompany.SpringH2JPA.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "items")
@Data
public class Item extends BaseEntity {

  @Column(name = "name", unique = true)
  private String name;
  @Column(name = "price")
  private BigDecimal price;
  @Column(name = "category")
  private String category;

}
