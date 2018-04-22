package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.*;

import javax.ejb.Local;
import java.util.List;

@Local
public interface UserManagementEJBLocal {

    User createUser(User user);

    ProUser createProUser(User user);

    Teacher createTeacher(User user);

    Student createStudent(User user, String school, String klas, int grNr);

    User findPerson(String login);

    ProUser findProUser(String login);

    Teacher findTeacher(String login);

    Student findStudent(String login);

    List<User> findAllUsers();

    UserToken createToken(User user);

    UserToken findToken(User user);

    <T> void updateDB(T t);







}
