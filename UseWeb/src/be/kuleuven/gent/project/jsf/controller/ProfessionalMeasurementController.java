package be.kuleuven.gent.project.jsf.controller;

import be.kuleuven.gent.project.data.ProfessionalMeasurement;
import be.kuleuven.gent.project.data.User;
import be.kuleuven.gent.project.ejb.ProfessionalMeasurementManagementEJBLocal;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Deze controller wordt aangesproken door de webpaginas. De controller zal op zijn beurt de correcte EJB aanroepen.
 * De controller wordt gebruikt voor het beheer van de Professionele metingen
 */
@Named
@ViewScoped
public class ProfessionalMeasurementController implements Serializable {

    private static final long serialVersionUID = 6737147724536164355L;

    @Inject
    private ProfessionalMeasurementManagementEJBLocal professionalMeasurementEJB;

    @Inject
    private UserController userController;

    private ProfessionalMeasurement professionalMeasurement = new ProfessionalMeasurement();
    private Long measurementId;

    /**
     * Voor het vinden van een meting
     */
    public void findMeasurement(){
        professionalMeasurement = professionalMeasurementEJB.findMeasurementById(measurementId);
    }

    /**
     * Het vinden van alle metingen van één gebruiker. De ProfessionalMeasurementEJB en userController worden gebruikt.
     * @return De lijst van professionele metingen wordt teruggegeven
     */
    public List<ProfessionalMeasurement> findAllMeasurementsByUser() {
        userController.findUser();
        User user = userController.getLoggedInUser();
        return professionalMeasurementEJB.findAllMeasurementsByUser(user);
    }

    /**
     * Voor het aanpassen van een meting, professionalMeasurementEJB wordt gebruikt
     * @return Een link naar de volgende pagina
     */
    public String editMeasurement(){
        int userCheck = checkUser();
        if (userCheck == 0){
            return "home?faces-redirect=true;";
        }
        professionalMeasurementEJB.updateDB(professionalMeasurement);
        if (userCheck == 2) {
            return "/Admin/projects?faces-redirect=true;";
        }
        return "ownMeasurements?faces-redirect=true;";
    }

    /**
     * Voor het verwijderen van een meting, professionalMeasurementEJB wordt gebruikt
     * @return Een link naar de volgende pagina
     */
    public String deleteMeasurement(){
        int userCheck = checkUser();
        if (userCheck == 0){
            return "home?faces-redirect=true;";
        }
        professionalMeasurementEJB.deleteMeasurement(professionalMeasurement);
        if (userCheck == 2){
            return "/Admin/projects?faces-redirect=true;";
        }
        return "ownMeasurements?faces-redirect=true;";
    }

    /**
     * Omleiding van een gebruiker
     * @return De link naar de volgende pagina
     */
    public String redirectUser(){
        int userCheck = checkUser();
        if (userCheck == 2) return "/Admin/projects?faces-redirect=true;";
        return "projects?faces-redirect=true;";
    }

    /**
     * Het controleren van een gebruiker. Er wordt gecontroleerd of de gebruiker een Admin is, een proUser
     * die een bijhorende meting heeft gedaan of een andere proUser.
     * @return een int die het type van de gebruiker weerspiegeld
     */
    public int checkUser(){
        userController.findUser();
        User loggedInUser = userController.getLoggedInUser();
        User contributor = professionalMeasurement.getProUser().getUser();
        if (!loggedInUser.getLogin().equals(contributor.getLogin()) && !loggedInUser.getGroup().equals("Admin")){
            return 0;
        }
        if (loggedInUser.getGroup().equals("Admin")) {
            return 2;
        }
        return 1;
    }


    public ProfessionalMeasurement getProfessionalMeasurement(){
        return professionalMeasurement;
    }

    public void setProfessionalMeasurement(ProfessionalMeasurement professionalMeasurement) {
        this.professionalMeasurement = professionalMeasurement;
    }

    public Long getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(Long measurementId) {
        this.measurementId = measurementId;
    }
}
