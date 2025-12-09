package com.pluralsight.persistence.web;

import java.math.BigDecimal;

public class CartItem {

  public enum ItemType {
    BOOK, CD
  }

  private Long itemId;
  private String title;
  private BigDecimal price;
  private int quantity;
  private ItemType itemType;

  public CartItem() {
  }

  public CartItem(Long itemId, String title, BigDecimal price, ItemType itemType) {
    this.itemId = itemId;
    this.title = title;
    this.price = price;
    this.quantity = 1;
    this.itemType = itemType;
  }

  public Long getItemId() {
    return itemId;
  }

  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public ItemType getItemType() {
    return itemType;
  }

  public void setItemType(ItemType itemType) {
    this.itemType = itemType;
  }

  public BigDecimal getSubtotal() {
    return price.multiply(BigDecimal.valueOf(quantity));
  }

  public void incrementQuantity() {
    this.quantity++;
  }
}
