package be.kuleuven.gent.project.jsf.controller;

import java.io.Serializable;
import java.util.logging.Logger;

import be.kuleuven.gent.project.data.User;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.logging.Logger;

@Named
@ViewScoped
public class RegisterController implements Serializable {

    private static final long serialVersionUID = 6737147724536164355L;

    private static Logger log = Logger.getLogger(RegisterController.class.getName());

    @Inject
    private UserManagementEJBLocal ejb;

    private User usr = new User();

    public String register() {
        ejb.createUser(usr);
        log.info("New user created with e-mail: " + usr.getLogin() + " and name: " + usr.getName());
        return "regdone";
    }

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }
}
