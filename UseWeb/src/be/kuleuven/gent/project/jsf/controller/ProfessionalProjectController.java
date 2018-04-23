package be.kuleuven.gent.project.jsf.controller;

import be.kuleuven.gent.project.data.ProfessionalMeasurement;
import be.kuleuven.gent.project.data.ProfessionalProject;
import be.kuleuven.gent.project.ejb.ProfessionalProjectManagementEJBLocal;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.util.List;

/**
 * Deze controller wordt aangesproken door de webpagina's. De controller zal op zijn beurt de correcte EJB aanroepen.
 * Deze controller is gebruikt om professionele projecten te beheren
 */
@Named
@ViewScoped
public class ProfessionalProjectController implements Serializable{
    private static final long serialVersionUID = 6737147724536164355L;

    @Inject
    private ProfessionalProjectManagementEJBLocal ProfessionalProjectEJB;

    private ProfessionalProject project;
    private Long projectId;

    public ProfessionalProject project(){
        return project;
    }

    /**
     * Default constructor
     */
    public ProfessionalProjectController() {
        //default constructor
    }

    /**
     * Gebruikt de ProfessionalProjectManagementEJB om alle projecten te vinden.
     * @return een lijst van alle Professionele projecten
     */
    public List<ProfessionalProject> findAllProjects() {
        return ProfessionalProjectEJB.findAllProjects();
    }

    /**
     * Het vinden van een project op basis van zijn projectID. Mbv de ProfessionalProjectManagementEJB
     */
    public void findProject() {
        project = ProfessionalProjectEJB.findProject(projectId);
    }

    /**
     * Vinden van alle metingen die behoren tot een project, gebruikt de ProfessionalProjectManagementEJB
     * @return een lijst van de metingen
     */
    public List<ProfessionalMeasurement> findAllMeasurements() {

        return ProfessionalProjectEJB.findAllMeasurements(projectId);
    }

    /**
     * Voor het aanpakken van de eigenschappen van een project
     * @return een link naar de volgende pagina
     */
    public String editProject(){
        ProfessionalProjectEJB.updateDB(project);
        return "projects?faces-redirect=true;";
    }

    /**
     * Voor het verwijderen van een project, gebruikt de ProfessionalProjectManagementEJB
     * @return een link naar de volgende pagina
     */
    public String deleteProject(){
        ProfessionalProjectEJB.deleteProject(project);
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
