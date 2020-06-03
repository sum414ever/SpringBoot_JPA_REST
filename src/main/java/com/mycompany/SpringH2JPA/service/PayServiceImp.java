package com.mycompany.SpringH2JPA.service;

import com.mycompany.SpringH2JPA.model.BaseEntity;
import com.mycompany.SpringH2JPA.model.Item;
import com.mycompany.SpringH2JPA.model.Order;
import com.mycompany.SpringH2JPA.model.Purchase;
import com.mycompany.SpringH2JPA.model.User;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImp implements PayService {

  @Autowired
  private DiscountService discountService;
  @Autowired
  private ItemService itemService;
  @Autowired
  private OrderService orderService;
  @Autowired
  private PurchaseService purchaseService;
  @Autowired
  private UserService userService;

  @Override
  public BigDecimal payAll(Long userId) {

    User user = userService.getById(userId);
    List<Order> orders = orderService.getAllByUserName(user.getName());
    List<Item> items = new ArrayList<>();
    for (Order o : orders) {
      items.add(itemService.getById(o.getItemId()));
    }

    Map<Long, Long> countOfItems = items.stream()
        .collect(Collectors.groupingBy(BaseEntity::getId, Collectors.counting()));

    List<BigDecimal> maxTotalDiscounts = new ArrayList<>();
    BigDecimal totalCost = new BigDecimal(0);

    for (Map.Entry<Long, Long> entry : countOfItems.entrySet()) {
      totalCost = totalCost.add(countTotalCost(entry.getKey(), entry.getValue()));
      BigDecimal discount = countTotalDiscountInMoney(entry.getKey(), entry.getValue());
      if (discount != null) {
        maxTotalDiscounts.add(discount);
      }
    }

    BigDecimal totalDiscount = maxTotalDiscounts.stream()
        .sorted(Comparator.reverseOrder())
        .limit(3)
        .reduce(new BigDecimal(0), BigDecimal::add);

    totalCost = totalCost.subtract(totalDiscount);

    boolean enoughMoney = enoughMoney(user.getBalance(), totalCost);

    if (enoughMoney) {
      for (Order order : orders) {
        orderService.delete(order.getId());
        purchaseService.save(new Purchase(userId, order.getItemId()));
      }

      user.setBalance(user.getBalance().subtract(totalCost));
      userService.save(user);
      return totalCost;
    } else {
      return null;
    }
  }

  @Override
  public BigDecimal payOne(Long userId, Long itemId) {

    User user = userService.getById(userId);
    Item item = itemService.getById(itemId);
    BigDecimal discount = discountService.getByItemId(itemId).getDiscount();

    BigDecimal itemCost = discount == null
        ? item.getPrice()
        : item.getPrice()
            .subtract(item.getPrice().multiply(discount.divide(new BigDecimal(100))));

    boolean enoughMoney = enoughMoney(user.getBalance(), itemCost);
    if (enoughMoney) {
      purchaseService.save(new Purchase(userId, itemId));
      user.setBalance(user.getBalance().subtract(itemCost));
      userService.save(user);
      return itemCost;
    } else {
      return null;
    }
  }

  public boolean enoughMoney(BigDecimal userBalance, BigDecimal cost) {

    if (userBalance.compareTo(cost) == 0) {
      return true;
    } else if (userBalance.compareTo(cost) == 1) {
      return true;
    } else {
      return false;
    }
  }

  public BigDecimal countTotalDiscountInMoney(Long itemId, Long itemsCount) {
    Item item = itemService.getById(itemId);

    BigDecimal itemDiscount = null;
    try {
      itemDiscount = discountService.getByItemId(itemId).getDiscount();
    } catch (NullPointerException e){
      return null;
    }

    BigDecimal itemCostWithoutDiscount = item.getPrice()
        .subtract(item.getPrice().multiply(itemDiscount.divide(new BigDecimal(100))));

    BigDecimal discountInMoney = item.getPrice().subtract(itemCostWithoutDiscount);

    return discountInMoney.multiply(new BigDecimal(itemsCount));
  }

  public BigDecimal countTotalCost(Long itemId, Long itemsCount) {
    Item item = itemService.getById(itemId);
    return item.getPrice().multiply(new BigDecimal(itemsCount));
  }
}
