package be.kuleuven.gent.project.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Entity
@Table(name="ProfessionalMeasurement")
public class ProfessionalMeasurement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idProfessionalMeasurement", nullable=false, length=16)
    private Long id;

    @OneToOne
    @JoinColumn(name="idData", nullable = false)
    private Data data;

    @Column(name = "professionalProject_id" , nullable = false, length = 11)
    private int idProject;

    @Column(name = "proUser_User_LoginName", nullable = false)
    private String loginUser;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date", nullable = false)
    private java.sql.Date date;

    public ProfessionalMeasurement(ProfessionalMeasurement professionalMeasurement) {
        this.id=professionalMeasurement.id;
        this.loginUser = professionalMeasurement.loginUser;
        this.description = professionalMeasurement.description;
        this.data = professionalMeasurement.data;
        this.idProject = professionalMeasurement.idProject;
        this.date = professionalMeasurement.date;
    }

    public ProfessionalMeasurement(Data data, int idProject, String loginUser, String description, java.sql.Date date) {
        this.data = data;
        this.idProject = idProject;
        this.loginUser = loginUser;
        this.description = description;
        this.date = date;
    }

    public ProfessionalMeasurement(){
        super();
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(String userID) {
        this.loginUser = userID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String beschrijving) {
        this.description = beschrijving;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }
}
