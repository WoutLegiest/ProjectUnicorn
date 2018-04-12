package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.ProUser;
import be.kuleuven.gent.project.data.Teacher;
import be.kuleuven.gent.project.data.User;
import be.kuleuven.gent.project.data.UserToken;

import javax.ejb.Local;

@Local
public interface UserManagementEJBLocal {

    User createUser(User user);

    ProUser createProUser(User user);

    User findPerson(String login);

    ProUser findProUser(String login);

    Teacher findTeacher(String login);

    UserToken createToken(User user);

    UserToken findToken(User user);

    void updateDB(User user);







}
