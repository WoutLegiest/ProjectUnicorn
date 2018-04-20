package be.kuleuven.gent.project.jsf.controller;

import be.kuleuven.gent.project.data.ProfessionalMeasurement;
import be.kuleuven.gent.project.data.ProfessionalProject;
import be.kuleuven.gent.project.ejb.ProfessionalProjectManagementEJBLocal;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.util.List;

@Named
@ViewScoped
public class SpotterProjectController implements Serializable{
    private static final long serialVersionUID = 6737147724536164355L;

    @Inject
    private ProfessionalProjectManagementEJBLocal spotterProjectEJB;

    private ProfessionalProject project;
    private Long projectId;

    public ProfessionalProject project(){
        return project;
    }

    public SpotterProjectController() {
        //default constructor
    }

    public List<ProfessionalProject> findAllProjects() {
        return spotterProjectEJB.findAllProjects();
    }

    public void findProject() {
        project = spotterProjectEJB.findProject(projectId);
    }

    public List<ProfessionalMeasurement> findAllMeasurements() {

        return spotterProjectEJB.findAllMeasurements(projectId);
    }

    public String editProject(){
        spotterProjectEJB.updateDB(project);
        return "projects?faces-redirect=true;";
    }

    public String deleteProject(){
        spotterProjectEJB.deleteProject(project);
        return "projects?faces-redirect=true;";
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public ProfessionalProject getProject() {
        return project;
    }

    public void setProject(ProfessionalProject project) {
        this.project = project;
    }
}
