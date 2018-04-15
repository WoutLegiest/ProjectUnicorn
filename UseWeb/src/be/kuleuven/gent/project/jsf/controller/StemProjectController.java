package be.kuleuven.gent.project.jsf.controller;

import be.kuleuven.gent.project.data.ProfessionalProject;
import be.kuleuven.gent.project.data.StemProject;
import be.kuleuven.gent.project.data.Teacher;
import be.kuleuven.gent.project.ejb.SpotterProjectManagementEJBLocal;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class StemProjectController {

    @Inject
    private StemProjectManagementEJBLocal stemProjectEJB;

    @Inject
    private UserManagementEJBLocal userEJB;

    private StemProject project = new StemProject();
    private Long projectId;
    private int numberOfGroups;

    public StemProjectController() {
    }

    public Teacher findTeacher(){
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String teacherLogin = ec.getRemoteUser();
        return userEJB.findTeacher(teacherLogin);
    }

    public void findAllProjectsByTeacher(){
        stemProjectEJB.findAllProjectsByTeacher(findTeacher());
    }

    public String newProject(){
        project.setTeacher(findTeacher());
        stemProjectEJB.createProject(project, numberOfGroups);
        return "projecten";
    }

    public StemProject getProject() {
        return project;
    }

    public void setProject(StemProject project) {
        this.project = project;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    public void setNumberOfGroups(int numberOfGroups) {
        this.numberOfGroups = numberOfGroups;
    }
}
