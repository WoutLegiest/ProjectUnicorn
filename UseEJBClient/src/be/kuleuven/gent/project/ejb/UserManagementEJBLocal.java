package be.kuleuven.gent.project.ejb;

import javax.ejb.Local;

import be.kuleuven.gent.project.data.User;

@Local
public interface UserManagementEJBLocal {

    public User createUser(User user);

    public User findPerson(String login);

    public String createToken(User user);

}
