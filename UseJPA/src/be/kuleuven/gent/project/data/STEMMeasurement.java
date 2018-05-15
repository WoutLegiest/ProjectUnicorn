package be.kuleuven.gent.project.data;

import com.sun.xml.xsom.impl.scd.Iterators;

import javax.persistence.*;
import javax.websocket.Encoder;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * In een STEMMeasurement object houden de info bij van een meting. Zo wordt de data van de meting bijgehouden door de idData op te slaan
 * en het bovenliggende project, waarbij de meting is aangesloten, opgeslaan door de idProject.
 * Dit object is specifiek voor STEM metingen te doen
 */
@XmlRootElement
@Entity
@Table(name="STEMMeasurement")
public class STEMMeasurement implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idSTEMMeasurement", nullable=false, length=11)
    private Long id;

    @Column(name="idSTEMProject", nullable=false, length=11)
    private Long idProject;

    @Column(name="idData", nullable=false, length=11)
    private Long idData;

    @Column(name = "student_Group_User_User_LoginName", nullable = false)
    private String userLogin;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date", nullable = false)
    private java.sql.Date date;

    public STEMMeasurement(Long idProject, Long idData, String userLogin, String description, java.sql.Date date) {
        this.idProject = idProject;
        this.idData = idData;
        this.userLogin = userLogin;
        this.date=date;
        this.description=description;
    }

    public STEMMeasurement(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userID) {
        this.userLogin = userID;
    }

    public Long getIdProject() {
        return idProject;
    }

    public void setIdProject(Long idProject) {
        this.idProject = idProject;
    }

    public Long getIdData() {
        return idData;
    }

    public void setIdData(Long idData) {
        this.idData = idData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
