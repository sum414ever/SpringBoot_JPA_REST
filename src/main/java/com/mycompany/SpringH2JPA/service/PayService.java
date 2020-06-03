package com.mycompany.SpringH2JPA.service;

import java.math.BigDecimal;

public interface PayService {

  BigDecimal payAll(Long userId);

  BigDecimal payOne(Long userId, Long itemId);

}
