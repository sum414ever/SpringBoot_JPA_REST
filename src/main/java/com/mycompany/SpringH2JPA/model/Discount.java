package com.mycompany.SpringH2JPA.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "discounts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Discount extends BaseEntity {

  @Column(name = "item_id")
  private long itemId;
  @Column(name = "discount")
  private BigDecimal discount;

}
