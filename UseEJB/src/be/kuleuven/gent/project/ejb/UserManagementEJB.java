package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.ProUser;
import be.kuleuven.gent.project.data.Teacher;
import be.kuleuven.gent.project.data.User;
import be.kuleuven.gent.project.data.UserToken;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Session Bean implementation class UserManagementEJB
 */

@Stateless
public class UserManagementEJB implements UserManagementEJBLocal {

    @PersistenceContext(unitName = "unicorn")
    private EntityManager em;

    public UserManagementEJB() {
        //default constructor
    }

    @Override
    public User createUser(User user) {
        try {
            user.sethPassword(Encryption.encodeSHA256(user.gethPassword()));
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

        em.persist(user);

        return user;
    }

    @Override
    public ProUser createProUser(User user) {
        ProUser proUser = new ProUser(user);
        em.persist(proUser);
		return proUser;
    }

    @Override
    public User findPerson(String login) {

        Query q = em.createQuery("SELECT p FROM User p WHERE p.login = :login");
        q.setParameter("login", login);
        List<User> persons = q.getResultList();

        if (persons.size() == 1)
            return persons.get(0);
        else return null;

    }

    @Override
    public ProUser findProUser(String login) {

		/*Query q = em.createQuery("SELECT p FROM ProUser p WHERE p.user.login LIKE :login");
		q.setParameter("login", login);
		List<ProUser> persons = q.getResultList();*/

        Query q = em.createNativeQuery("SELECT * FROM ProUser", ProUser.class);
        List<ProUser> persons = q.getResultList();

        System.out.println("hdhdhd");

        for (ProUser proUser : persons) {
            if (proUser.getUser().getLogin() == login) {
                return proUser;
            }
        }
        return null;

		/*if(persons.size() == 1)
			return persons.get(0);
		else return null;*/

    }

    @Override
    public Teacher findTeacher(String login) {

        Query q = em.createQuery("SELECT p FROM Teacher p WHERE p.user.login = :login");
        q.setParameter("login", login);
        List<Teacher> persons = q.getResultList();

        if (persons.size() == 1)
            return persons.get(0);
        else return null;

    }

    @Override
    public UserToken createToken(User user) {
        byte[] array = new byte[45]; // length is bounded by 45
        new Random().nextBytes(array);
        String generatedString = new String(array, Charset.forName("UTF-8"));
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        UserToken userToken = new UserToken(user.getLogin(), generatedString, sqlDate);
        em.persist(userToken);
        return userToken;
    }

    @Override
    public UserToken findToken(User user) {
        Query q = em.createQuery("SELECT p FROM UserToken p WHERE p.loginName = :login");
        q.setParameter("login", user.getLogin());
        List<UserToken> userTokens = q.getResultList();

        if (userTokens.size() == 1)
            return userTokens.get(0);
        else return null;
    }

    @Override
    public void updateDB(User user) {
        /*try {
            user.sethPassword(Encryption.encodeSHA256(user.gethPassword()));
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }*/
        em.merge(user);
    }

}
