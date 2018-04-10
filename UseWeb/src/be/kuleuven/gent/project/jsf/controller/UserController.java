package be.kuleuven.gent.project.jsf.controller;

import be.kuleuven.gent.project.data.User;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;

import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;


@Named
@Stateless
public class UserController implements Serializable  {

    private static final long serialVersionUID = 6737147724536164355L;

    @Inject
    private UserManagementEJBLocal userEJB;

    private User loggedInUser;

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public void logout() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        try {
            ec.redirect(ec.getRequestContextPath() + "/index.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void findUser() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String userName = ec.getRemoteUser();
        loggedInUser = userEJB.findPerson(userName);
    }

    public void changeData() {
        userEJB.updateDB(loggedInUser);
    }
}

