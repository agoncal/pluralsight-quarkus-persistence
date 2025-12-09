package com.pluralsight.persistence.web;

import com.pluralsight.persistence.web.catalog.UserDTO;
import com.pluralsight.persistence.web.catalog.UserRole;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;

@ApplicationScoped
@Named("userSession")
public class UserSession {

  private UserDTO currentUser;

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
}
