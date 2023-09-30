package sax;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import model.Role;
import model.User;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXUserParsing extends DefaultHandler {

  private User user;
  private Role role;
  private List<User> users;
  private StringBuilder characters;

  @Override
  public void characters(char[] ch, int start, int length) {
    characters = new StringBuilder();
    characters.append(ch, start, length);
  }

  @Override
  public void startElement(String uri, String localName, String qName, Attributes attributes) {

    switch (qName) {
      case "users" -> users = new LinkedList<>();
      case "user" -> user = new User();
      case "roles" -> user.setRoles(new LinkedList<>());
      case "role" -> role = new Role();
    }
  }

  @Override
  public void endElement(String uri, String localName, String qName) {
    switch (qName) {
      case "user" -> users.add(user);
      case "userName" -> user.setUserName(characters.toString());
      case "email" -> user.setEmail(characters.toString());
      case "role" -> user.getRoles().add(role);
      case "name" -> role.setName(characters.toString());
      case "description" -> role.setDescription(characters.toString());
    }
  }

  public List<User> parseUsers(File resource)
      throws ParserConfigurationException, SAXException, IOException {

    SAXParserFactory parserFactory = SAXParserFactory.newDefaultInstance();

    SAXParser parser = parserFactory.newSAXParser();

    parser.parse(resource, this);

    return users;
  }
}