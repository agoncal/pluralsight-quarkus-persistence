package com.pluralsight.persistence.web;

import com.pluralsight.persistence.web.catalog.UserDTO;
import com.pluralsight.persistence.web.catalog.UserRole;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Named("userSession")
public class UserSession {

  private UserDTO currentUser;
  private List<CartItem> cart = new ArrayList<>();

  public UserDTO getCurrentUser() {
    return currentUser;
  }

  public void setCurrentUser(UserDTO currentUser) {
    this.currentUser = currentUser;
  }

  public boolean isLoggedIn() {
    return currentUser != null;
  }

  public String getUsername() {
    return currentUser != null ? currentUser.username : null;
  }

  public boolean isAdmin() {
    return currentUser != null && currentUser.role == UserRole.ADMIN;
  }

  public void logout() {
    this.currentUser = null;
  }

  // Cart methods

  public List<CartItem> getCart() {
    return cart;
  }

  public void addToCart(Long itemId, String title, BigDecimal price, CartItem.ItemType itemType) {
    Optional<CartItem> existingItem = cart.stream()
        .filter(item -> item.getItemId().equals(itemId) && item.getItemType() == itemType)
        .findFirst();

    if (existingItem.isPresent()) {
      existingItem.get().incrementQuantity();
    } else {
      cart.add(new CartItem(itemId, title, price, itemType));
    }
  }

  public void removeFromCart(Long itemId, CartItem.ItemType itemType) {
    cart.removeIf(item -> item.getItemId().equals(itemId) && item.getItemType() == itemType);
  }

  public void clearCart() {
    cart.clear();
  }

  public int getCartItemCount() {
    return cart.stream().mapToInt(CartItem::getQuantity).sum();
  }

  public BigDecimal getCartTotal() {
    return cart.stream()
        .map(CartItem::getSubtotal)
        .reduce(BigDecimal.ZERO, BigDecimal::add);
  }

  public boolean isCartEmpty() {
    return cart.isEmpty();
  }
}
