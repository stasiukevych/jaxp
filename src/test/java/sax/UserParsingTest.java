package sax;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

class UserParsingTest {

  private List<User> expectedUsers;

  @BeforeEach
  public void setUp() {
    Role adminRole = new Role();
    adminRole.setName("admin");
    adminRole.setDescription("admin mode");

    Role userRole = new Role();
    userRole.setName("user");
    userRole.setDescription("read-only mode");

    Role dba = new Role();
    dba.setName("dba");
    dba.setDescription("database mode");

    expectedUsers = new LinkedList<>();
    User user = new User();
    user.setUserName("anatolii");
    user.setEmail("anatolii@gmail.com");
    user.setRoles(List.of(userRole, dba));

    expectedUsers.add(user);

    User admin = new User();
    admin.setUserName("sera");
    admin.setEmail("sera@gmail.com");
    admin.setRoles(List.of(adminRole));
    expectedUsers.add(admin);
  }

  @Test
  public void parsingUserShouldReturnFoundUsers()
      throws ParserConfigurationException, IOException, SAXException {

    List<User> users = new UserParsing().parseUsers(new File("src/test/resources/users.xml"));

    assertNotNull(users);
    assertEquals(users.size(), 2);

    assertEquals(expectedUsers, users);
    users.forEach(System.out::println);
  }
}