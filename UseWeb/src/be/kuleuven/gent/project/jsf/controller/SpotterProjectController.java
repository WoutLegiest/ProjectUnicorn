package be.kuleuven.gent.project.jsf.controller;

import java.io.Serializable;
import java.util.List;

//import javax.annotation.PostConstruct;
import javax.ejb.EJB;

//import javax.ejb.Stateless;
//import javax.enterprise.context.SessionScoped;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import be.kuleuven.gent.project.data.SpotterProject;
import be.kuleuven.gent.project.ejb.SpotterProjectManagementEJBLocal;

@Named
@ViewScoped
public class SpotterProjectController implements Serializable{
    private static final long serialVersionUID = 6737147724536164355L;

    @Inject
    private SpotterProjectManagementEJBLocal spotterProjectEJB;

    private SpotterProject project = new SpotterProject();

    public SpotterProjectController() {
    }

    public List<SpotterProject> findProjects() {

        return spotterProjectEJB.findProjects();
    }

    public SpotterProject getProject() {
        return project;
    }

    public void setProject(SpotterProject project) {
        this.project = project;
    }
}
