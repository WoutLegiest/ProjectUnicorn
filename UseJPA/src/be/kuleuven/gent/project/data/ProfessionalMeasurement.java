package be.kuleuven.gent.project.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Entity
@Table(name="Spottermeting")
public class ProfessionalMeasurement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SpotterProject_id", nullable=false, length=16)
    private Long id;

    @Column(name = "ProUser_Group_User_User_LoginNaam", nullable = false)
    private String userID;

    @Column(name = "Description", nullable = false)
    private String description;

    public ProfessionalMeasurement(ProfessionalMeasurement professionalMeasurement)
    {
        this.id=professionalMeasurement.id;
        this.userID = professionalMeasurement.userID;
        this.description = professionalMeasurement.description;

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

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String beschrijving) {
        this.description = beschrijving;
    }
}
