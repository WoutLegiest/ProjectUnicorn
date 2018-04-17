package be.kuleuven.gent.project.jsf.controller;

import be.kuleuven.gent.project.data.ProfessionalMeasurement;
import be.kuleuven.gent.project.data.User;
import be.kuleuven.gent.project.ejb.ProfessionalMeasurementManagementEJBLocal;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

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

    public void findMeasurement(){
        professionalMeasurement = professionalMeasurementEJB.findMeasurementById(measurementId);
    }

    public List<ProfessionalMeasurement> findAllMeasurementsByUser() {
        userController.findUser();
        User user = userController.getLoggedInUser();
        return professionalMeasurementEJB.findAllMeasurementsByUser(user);
    }

    public String editMeasurement(){
        userController.findUser();
        if (userController.getLoggedInUser().getLogin().equals(professionalMeasurement.getProUser().getUser().getLogin())){
            professionalMeasurementEJB.updateDB(professionalMeasurement);
            return "ownMeasurements";
        } else return "home";


    }

    public ProfessionalMeasurement getProfessionalMeasurement() {
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
