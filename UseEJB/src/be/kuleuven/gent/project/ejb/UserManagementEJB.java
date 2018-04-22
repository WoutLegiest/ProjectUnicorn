package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * EJB voor het beheren van de gebruiker
 */
@Stateless
public class UserManagementEJB implements UserManagementEJBLocal {

    @PersistenceContext(unitName = "unicorn")
    private EntityManager em;

    /**
     * Default constructor
     */
    public UserManagementEJB() {
        //default constructor
    }

    /**
     * Het gekregen user object zijn wachtwoord coderen en op de database plaatsen
     * @param user De user die moet worden toegevoegd
     * @return De gebruiker inclusief gecodeerd wachtwoord
     */
    @Override
    public User createUser(User user) {
        try {
            user.sethPassword(encodeSHA256(user.gethPassword()));
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }

        em.persist(user);

        return user;
    }

    /**
     * Methode voor het coderen van het wachtwoord. Er wordt de SHA-256 codering gebruikt
     * @param password Het ongecodeerde wachtwoord
     * @return Het geëncrypteerde wachtwoord
     * @throws UnsupportedEncodingException
     * @throws NoSuchAlgorithmException
     */
    @Override
    public String encodeSHA256(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes("UTF-8"));
        byte[] digest = md.digest();
        return DatatypeConverter.printBase64Binary(digest).toString();
    }

    /**
     * Aanmaken van een ProUser op basis van een User object
     * @param user de basis waarvan we een ProUser object maken
     * @return Het gemaakte ProUser object
     */
    @Override
    public ProUser createProUser(User user) {
        ProUser proUser = new ProUser(user);
        em.persist(proUser);
		return proUser;
    }

    /**
     * Aanmaken van een Teacher op basis van een User object
     * @param user de basis waarvan we een Teacher object maken
     * @return Het gemaakte Teacher object
     */
    @Override
    public Teacher createTeacher(User user) {
        Teacher teacher = new Teacher(user);
        em.persist(teacher);
        return teacher;
    }

    /**
     * Aanmaken van een Student op basis van een User object
     * @param user user de basis waarvan we een Student object maken
     * @param school De bijhorende school
     * @param klas De bijhorende klas
     * @param grNr Het bijhorende groepsnummer
     * @return Het gemaakte Student object
     */
    @Override
    public Student createStudent(User user, String school, String klas, int grNr){
        Student student = new Student(user, school, klas, grNr);
        em.persist(student);
        return student;
    }

    /**
     * Vinden van een gebruiker op basis van zijn login
     * @param login De string login
     * @return Het gebruiker object van die login
     */
    @Override
    public User findPerson(String login) {

        Query q = em.createQuery("SELECT p FROM User p WHERE p.login = :login");
        q.setParameter("login", login);
        List<User> persons = q.getResultList();

        if (persons.size() == 1)
            return persons.get(0);
        else return null;

    }

    /**
     * Vinden van een proUser op basis van zijn login
     * @param login
     * @return het proUser object
     */
    @Override
    public ProUser findProUser(String login) {

        Query q = em.createNativeQuery("SELECT * FROM ProUser", ProUser.class);
        List<ProUser> persons = q.getResultList();

        for (ProUser proUser : persons) {
            if (proUser.getUser().getLogin().equals(login)) {
                return proUser;
            }
        }
        return null;
    }

    /**
     * Vinden van een leerkacht op basis van zijn loginnaam
     * @param login
     * @return Het teacher object
     */
    @Override
    public Teacher findTeacher(String login) {

        Query q = em.createNativeQuery("SELECT * FROM Teacher", Teacher.class);
        List<Teacher> persons = q.getResultList();

        for (Teacher teacher : persons) {
            if (teacher.getUser().getLogin().equals(login)) {
                return teacher;
            }
        }
        return null;

    }

    /**
     * Vinden van een student op basis van zijn loginnaam
     * @param login
     * @return Het student object
     */
    @Override
    public Student findStudent(String login) {

        Query q = em.createNativeQuery("SELECT * FROM Student", Teacher.class);
        List<Student> persons = q.getResultList();

        for (Student student : persons) {
            if (student.getUser().getLogin().equals(login)) {
                return student;
            }
        }
        return null;

    }

    /**
     * Alle gebruiker worden gezocht en teruggeven
     * @return Een lijst van alle gebruikers
     */
    @Override
    public List<User> findAllUsers() {
        Query q = em.createNativeQuery("SELECT * FROM User", User.class);
        List<User> persons = q.getResultList();
        return persons;
    }

    /**
     * Een Token wordt gecreëerd bij een User
     * @param user user waarbij de token wordt toegevoegd
     * @return De Usertoken wordt teruggegeven
     */
    @Override
    public UserToken createToken(User user) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String generatedString = salt.toString();
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        UserToken userToken = new UserToken(user.getLogin(), generatedString, sqlDate);
        em.persist(userToken);
        return userToken;
    }

    /**
     * Vinden van een token van een user
     * @param user Van deze user waarvan de token wordt gezocht
     * @return De usertoken
     */
    @Override
    public UserToken findToken(User user) {
        Query q = em.createQuery("SELECT p FROM UserToken p WHERE p.loginName = :login");
        q.setParameter("login", user.getLogin());
        List<UserToken> userTokens = q.getResultList();

        if (userTokens.size() == 1)
            return userTokens.get(0);
        else return null;
    }

    /**
     * Check of de token ouder is dan één dag
     * @param login De loginnaam van een user
     * @return een boolean, true als ze niet ouder zijn dan een dag en false als ze wel ouder zijn dan een dag
     */
    public boolean checkTokenOnDate(String login){
        Query q = em.createQuery("SELECT p FROM UserToken p WHERE p.loginName = : login", UserToken.class);
        q.setParameter("login", login);
        List<UserToken> userTokens = q.getResultList();

        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

        if (userTokens.size() == 1){
            java.sql.Date tokenDate = userTokens.get(0).getDate();

            if(sqlDate.compareTo(tokenDate)==0)
                return true;
            else if (sqlDate.compareTo(tokenDate)<0)
                return false;

        }
            return false;
    }

    //public boolean checkTokenOnDate(String login)
    //Zoek token en als datum ouder is al ne dag, false teruggeven

    /**
     * Generische methode op gebruiker aan te passen in de database
     * @param t
     * @param <T>
     */
    @Override
    public <T> void updateDB(T t) {

        em.merge(t);
    }

}
