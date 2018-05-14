package be.kuleuven.gent.project.jsf.controller;

import be.kuleuven.gent.project.data.STEMMeasurement;
import be.kuleuven.gent.project.data.Student;
import be.kuleuven.gent.project.data.User;
import be.kuleuven.gent.project.ejb.StemProjectManagementEJBLocal;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@Stateless
public class StemMeasurementController implements Serializable {

    private static final long serialVersionUID = 792279536138267237L;

    @Inject
    private StemProjectManagementEJBLocal stemProjectEJB;

    @Inject
    private UserManagementEJBLocal userEJB;

    private Student student;
    private User user;
    private String studentLogin;

    public StemMeasurementController() {
    }

    public void findStudent(){
        //student = userEJB.findStudent(studentLogin);
        user = userEJB.findPerson(studentLogin);
    }

    public List<STEMMeasurement> getAllMeasurementsByStudent(){
        return stemProjectEJB.findAllMeasurementsByStudent(user);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
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
}
