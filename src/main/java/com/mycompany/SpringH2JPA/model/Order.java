package com.mycompany.SpringH2JPA.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {

  @Column(name = "user_Id")
  private long userId;
  @Column(name = "item_Id")
  private long itemId;
}
