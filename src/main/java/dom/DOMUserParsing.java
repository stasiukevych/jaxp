package dom;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import model.Role;
import model.User;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOMUserParsing {

  private User currentUser;
  private Role currentRole;
  private List<Role> rolesList;
  private List<User> userList;

  public List<User> parseUsers(File resource)
      throws ParserConfigurationException, IOException, SAXException {

    DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document document = builder.parse(resource);
    document.getDocumentElement().normalize();

    Node root = document.getElementsByTagName("users").item(0);

    userList = new LinkedList<>();

    NodeList users = root.getChildNodes();

    Node current = null;

    for (int i = 0; i < users.getLength(); i++) {
      current = users.item(i);
      if (current.getNodeName().equals("user")) {
        rolesList = null;
        currentUser = new User();

        NodeList userChildren = current.getChildNodes();

        for (int j = 0; j < userChildren.getLength(); j++) {
          current = userChildren.item(j);
          switch (current.getNodeName()) {
            case "userName" -> currentUser.setUserName(current.getTextContent());
            case "email" -> currentUser.setEmail(current.getTextContent());
            case "roles" -> {
              NodeList roles = current.getChildNodes();
              rolesList = new LinkedList<>();

              for (int k = 0; k < users.getLength(); k++) {
                current = roles.item(k);

                if (current != null && current.getNodeName().equals("role")) {
                  NodeList role = current.getChildNodes();
                  currentRole = new Role();

                  for (int l = 0; l < role.getLength(); l++) {
                    current = role.item(l);

                    switch (current.getNodeName()) {
                      case "name" -> currentRole.setName(current.getTextContent());
                      case "description" -> currentRole.setDescription(current.getTextContent());
                    }
                  }

                  if (currentRole != null && rolesList != null) {
                    rolesList.add(currentRole);
                  }
                }
              }
            }
          }
        }
        if (currentUser != null) {
          currentUser.setRoles(rolesList);
          userList.add(currentUser);
        }
      }
    }

    return userList;
  }

  public User getCurrentUser() {
    return currentUser;
  }

  public Role getCurrentRole() {
    return currentRole;
  }

  public List<Role> getRolesList() {
    return rolesList;
  }

  public List<User> getUserList() {
    return userList;
  }
}