package be.kuleuven.gent.project.jsf.controller;

import be.kuleuven.gent.project.data.ProfessionalMeasurement;
import be.kuleuven.gent.project.ejb.ProfessionalMeasurementManagementEJBLocal;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class ProfessionalMeasurementController {

    private static final long serialVersionUID = 6737147724536164355L;

    @Inject
    private ProfessionalMeasurementManagementEJBLocal professionalMeasurementEJB;

    private ProfessionalMeasurement professionalMeasurement = new ProfessionalMeasurement();
    private Long measurementId;

    public void findMeasurement(){
        professionalMeasurement = professionalMeasurementEJB.findMeasurementById(measurementId);
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
