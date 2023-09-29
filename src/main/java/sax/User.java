package sax;

import java.util.List;
import java.util.Objects;

public class User {

  private String userName;
  private String email;
  private List<Role> roles;

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(userName, user.userName) && Objects.equals(email,
        user.email) && Objects.equals(roles, user.roles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userName, email, roles);
  }

  @Override
  public String toString() {
    return "User{" +
        "userName='" + userName + '\'' +
        ", email='" + email + '\'' +
        ", roles=" + roles +
        '}';
  }
}