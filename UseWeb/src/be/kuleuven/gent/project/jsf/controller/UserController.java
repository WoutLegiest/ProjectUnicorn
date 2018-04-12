package be.kuleuven.gent.project.jsf.controller;

import be.kuleuven.gent.project.data.ProUser;
import be.kuleuven.gent.project.data.Teacher;
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
    private String group;
    private String organisation;
    private String tag;

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

        group = loggedInUser.getGroup();

        if (group.equals("ProUser")) {
            tag = "Company";
            organisation = userEJB.findProUser(userName).getCompany();
        } else if (group.equals("Teacher")) {
            tag = "School";
            organisation = userEJB.findTeacher(userName).getSchool();
        } else {
            tag = "organisation";
            organisation = "n.v.t.";
        }
    }

    public void changeData() {
        userEJB.updateDB(loggedInUser);

        if (group.equals("ProUser")) {
            ProUser proUser = userEJB.findProUser(loggedInUser.getLogin());
            proUser.setCompany(organisation);
            userEJB.updateDB(proUser);
        } else if (group.equals("Teacher")) {
            Teacher teacher = userEJB.findTeacher(loggedInUser.getLogin());
            teacher.setSchool(organisation);
            userEJB.updateDB(teacher);
        }
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}

