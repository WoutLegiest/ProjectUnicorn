package be.kuleuven.gent.project.jsf.controller;

import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;


@Named
@Stateless
public class UserController implements Serializable{

    private static final long serialVersionUID = 6737147724536164355L;

    public String activateProUser(){
        return "ProUser/hello.faces?faces-redirect=true";
    }

    public void logout() {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        try {
            ec.redirect(ec.getRequestContextPath() + "/index.faces");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

