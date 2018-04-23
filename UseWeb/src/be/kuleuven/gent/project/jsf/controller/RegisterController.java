package be.kuleuven.gent.project.jsf.controller;

import be.kuleuven.gent.project.data.User;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

/**
 * Deze controller wordt aangesproken door de webpaginas. De controller zal op zijn beurt de correcte EJB aanroepen.
 * De controller dient op nieuwe gebruiker aan te maken
 */
@Named
@ViewScoped
public class RegisterController implements Serializable {

    private static final long serialVersionUID = 6737147724536164355L;

    private static Logger log = Logger.getLogger(RegisterController.class.getName());

    @Inject
    private UserManagementEJBLocal ejb;

    private List<User> users;

    private User usr = new User();

    /**
     * Voor het registeren van een gebruiker. Gebruikt de UserManagementEJB op de users aan te maken.
     * @return De webpagina die bevestigd dat er een gebruiker is aangemaakt.
     */
    public String register() {

        ejb.createUser(usr);
        if (usr.getGroup().equals("ProUser")) {
            ejb.createProUser(usr);
        } else if (usr.getGroup().equals("Teacher")) {
            ejb.createTeacher(usr);
        }

        log.info("New user created with e-mail: " + usr.getLogin() + " and name: " + usr.getFirstName() + " " + usr.getLastName());
        return "regdone";
    }

    /**
     * Deze gebruikt de UserManagementEJB om alle gebruikers te zoeken.
     */
    public void findAllUsers(){
        users = ejb.findAllUsers();
    }

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }
}
