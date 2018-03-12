package be.kuleuven.gent.project.ejb;

import java.util.List;
import java.util.logging.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import be.kuleuven.gent.project.data.User;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;

/**
 * Session Bean implementation class UserManagementEJB
 */

@Stateless
public class UserManagementEJB implements UserManagementEJBLocal {
	
	@PersistenceContext(unitName="unicorn")
	private EntityManager em;

	public UserManagementEJB() {
		// TO DO
	}

	@Override
	public User createUser(User user) {
		try {
			user.sethPassword(Encryptie.encodeSHA256(user.gethPassword()));
		} catch (Exception e) {
			Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
			e.printStackTrace();
		}

		em.persist(user);

		return user;
	}

	@Override
	public User findPerson(String login) {
		
		Query q = em.createQuery("SELECT p FROM User p WHERE p.login = :login");
		q.setParameter("login", login);
		List<User> persons = q.getResultList();
		
		if(persons.size() == 1)
			return persons.get(0);
		else return null;

	}

}
