package be.kuleuven.gent.project.jsf.controller;

import be.kuleuven.gent.project.data.STEMMeasurement;
import be.kuleuven.gent.project.data.User;
import be.kuleuven.gent.project.ejb.StemMeasurementManagementEJBLocal;
import be.kuleuven.gent.project.ejb.StemProjectManagementEJBLocal;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class StemMeasurementController implements Serializable {

    private static final long serialVersionUID = 792279536138267237L;

    @Inject
    private StemProjectManagementEJBLocal stemProjectEJB;

    @Inject
    private StemMeasurementManagementEJBLocal stemMeasurementEJB;

    @Inject
    private UserManagementEJBLocal userEJB;

    private User user;
    private String studentLogin;

    private STEMMeasurement measurement;
    private Long measurementId;

    public StemMeasurementController() {
    }

    public void findStudent(){
        //student = userEJB.findStudent(studentLogin);
        user = userEJB.findPerson(studentLogin);
    }

    public void findMeasurement(){
        measurement = stemMeasurementEJB.findMeasurementById(measurementId);
    }

    public List<STEMMeasurement> getAllMeasurementsByStudent(){
        return stemProjectEJB.findAllMeasurementsByStudent(user);
    }

    public String getStudentLogin() {
        return studentLogin;
    }

    public void setStudentLogin(String studentLogin) {
        this.studentLogin = studentLogin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public STEMMeasurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(STEMMeasurement measurement) {
        this.measurement = measurement;
    }

    public Long getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(Long measurementId) {
        this.measurementId = measurementId;
    }
}
