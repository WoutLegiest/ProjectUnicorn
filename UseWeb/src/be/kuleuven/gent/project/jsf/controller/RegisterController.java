package be.kuleuven.gent.project.jsf.controller;

import be.kuleuven.gent.project.data.User;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Logger;

@Named
@ViewScoped
public class RegisterController implements Serializable {

    private static final long serialVersionUID = 6737147724536164355L;

    private static Logger log = Logger.getLogger(RegisterController.class.getName());

    @Inject
    private UserManagementEJBLocal ejb;

    private User usr = new User();

    private String pass;
    private String confirmPass;


    public void validatePassword(ComponentSystemEvent event) {

        FacesContext facesContext = FacesContext.getCurrentInstance();

        UIComponent components = event.getComponent();

        // get password

        UIInput uiInputPassword = (UIInput) components.findComponent("password");

        String password = uiInputPassword.getLocalValue() == null ? "" : uiInputPassword.getLocalValue().toString();

        String passwordId = uiInputPassword.getClientId();

        // get confirm password

        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmpassword");

        String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
                : uiInputConfirmPassword.getLocalValue().toString();

        // Let required="true" do its job.

        if (password.isEmpty() || confirmPassword.isEmpty()) {
            return;
        }
        if (!password.equals(confirmPassword)) {
            FacesMessage msg = new FacesMessage("Confirm password does not match password");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(passwordId, msg);
            facesContext.renderResponse();
        }
        /*
        if (ejb.findUserById(usr.getEmail()) != null) {
            FacesMessage msg = new FacesMessage("User with this e-mail already exists");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage(passwordId, msg);
            facesContext.renderResponse();
        }
        */
    }

    public String register() {
        System.out.println("test");
        ejb.createUser(usr);
        if (usr.getGroup().equals("ProUser")) {
            ejb.createProUser(usr);
        } else if (usr.getGroup().equals("Teacher")) {
            ejb.createTeacher(usr);
        }

        log.info("New user created with e-mail: " + usr.getLogin() + " and name: " + usr.getFirstName() + " " + usr.getLastName());
        return "regdone";
    }

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }
}
