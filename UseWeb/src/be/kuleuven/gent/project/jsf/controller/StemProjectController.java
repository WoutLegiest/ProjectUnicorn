package be.kuleuven.gent.project.jsf.controller;

import be.kuleuven.gent.project.data.StemProject;
import be.kuleuven.gent.project.data.Student;
import be.kuleuven.gent.project.data.Teacher;
import be.kuleuven.gent.project.ejb.StemProjectManagementEJBLocal;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;

import javax.ejb.Stateless;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Deze controller wordt aangesproken door de webpagina's. De controller zal op zijn beurt de correcte EJB aanroepen.
 * De controller dient om alle StemProjecten te beheren
 */
@Named
@Stateless
public class StemProjectController implements Serializable {

    private static final long serialVersionUID = 7922795361382672937L;

    @Inject
    private StemProjectManagementEJBLocal stemProjectEJB;

    @Inject
    private UserManagementEJBLocal userEJB;

    private StemProject project = new StemProject();
    private Long projectId;
    private int numberOfGroups;

    public StemProjectController() {
    }

    /**
     * Vinden van welke Teacher er is ingelogd
     * @return Het teacher object dat is ingelogd
     */
    public Teacher findTeacher(){
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        String teacherLogin = ec.getRemoteUser();
        return userEJB.findTeacher(teacherLogin);
    }

    /**
     * Vinden van alle projecten die bij een bepaalde leerkracht horen. Gebruikt de StemProjectManagementEJB
     */
    public List<StemProject> findAllProjectsByTeacher(){
        return stemProjectEJB.findAllProjectsByTeacher(findTeacher());
    }

    /**
     * Vinden van alle studenten die aan een gegeven project deelnemen.
     */
    public List<Student> findStudentsForProject(){
        return userEJB.findAllStudentsForProject(project);
    }

    /**
     * Aanmaken van een nieuw project, gebruikt de StemProjectManagementEJB
     * @return Een string "projects"
     */
    public String newProject(){
        project.setTeacher(findTeacher());
        stemProjectEJB.createProject(project, numberOfGroups);
        return "projecten?faces-redirect=true";
    }

    /**
     * Het vinden van een project op basis van zijn projectID.
     */
    public void findProject() {
        project = stemProjectEJB.findProject(projectId);
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
