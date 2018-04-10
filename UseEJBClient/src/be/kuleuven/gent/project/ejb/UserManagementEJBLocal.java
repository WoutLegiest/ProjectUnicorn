package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.User;

import javax.ejb.Local;

@Local
public interface UserManagementEJBLocal {

    User createUser(User user);

    User findPerson(String login);

    String createToken(User user);

    void updateDB(User user);







}
