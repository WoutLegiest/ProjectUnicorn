package be.kuleuven.gent.project.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Entity
@Table(name="ProfessionalMeasurement")
public class ProfessionalMeasurement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="idProfessionalMeasurement", nullable=false, length=16)
    private Long id;

    @Column(name="idData", nullable = false, length = 11)
    private int idData;

    @Column(name = "professionalProject_id" , nullable = false, length = 11)
    private int idProject;

    @Column(name = "proUser_Group_User_User_LoginName", nullable = false)
    private String idUser;

    @Column(name = "description", nullable = false)
    private String description;

    public ProfessionalMeasurement(ProfessionalMeasurement professionalMeasurement) {
        this.id=professionalMeasurement.id;
        this.idUser = professionalMeasurement.idUser;
        this.description = professionalMeasurement.description;
        this.idData = professionalMeasurement.idData;
        this.idProject = professionalMeasurement.idProject;
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

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String userID) {
        this.idUser = userID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String beschrijving) {
        this.description = beschrijving;
    }

    public int getIdData() {
        return idData;
    }

    public void setIdData(int idData) {
        this.idData = idData;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }
}
