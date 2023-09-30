package model;

import java.util.Objects;

public class Role {

  private String name;
  private String description;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Role role = (Role) o;
    return Objects.equals(name, role.name) && Objects.equals(description,
        role.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description);
  }

  @Override
  public String toString() {
    return "Role{" +
        "name='" + name + '\'' +
        ", description='" + description + '\'' +
        '}';
  }
}