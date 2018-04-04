package be.kuleuven.gent.project.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

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

    public STEMMeasurement(STEMMeasurement STEMMeasurement){
        this.id= STEMMeasurement.id;
        this.userLogin= STEMMeasurement.userLogin;
        this.idProject = STEMMeasurement.idProject;
        this.idData = STEMMeasurement.idData;
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
}
