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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Named
@ViewScoped
public class RegisterController implements Serializable {

    private static final long serialVersionUID = 6737147724536164355L;

    private static Logger log = Logger.getLogger(RegisterController.class.getName());

    @Inject
    private UserManagementEJBLocal ejb;

    private List<User> users;

    private User usr = new User();

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

    public void validatePassword(ComponentSystemEvent event) {

        FacesContext fc = FacesContext.getCurrentInstance();

        UIComponent components = event.getComponent();

        // get password
        UIInput uiInputPassword = (UIInput) components.findComponent("password");
        String password = uiInputPassword.getLocalValue() == null ? ""
                : uiInputPassword.getLocalValue().toString();
        String passwordId = uiInputPassword.getClientId();
        String confirmPasswordId = components.findComponent("confirmPassword").getClientId();

        // get confirm password
        UIInput uiInputConfirmPassword = (UIInput) components.findComponent("confirmPassword");
        String confirmPassword = uiInputConfirmPassword.getLocalValue() == null ? ""
                : uiInputConfirmPassword.getLocalValue().toString();

        // Let required="true" do its job.
        if (password.isEmpty() || confirmPassword.isEmpty()) {
            return;
        }

        if (!password.equals(confirmPassword)) {

            FacesMessage msg = new FacesMessage("Password must match confirm password");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            fc.addMessage(confirmPasswordId, msg);
            fc.renderResponse();

        }

    }

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
