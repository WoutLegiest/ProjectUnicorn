package be.kuleuven.gent.project.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Entity
@Table(name="Spottermeting")
public class Spottermeting implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="SpotterProject_id", nullable=false, length=16)
    private Long id;

    @Column(name = "ProUser_Group_User_User_LoginNaam", nullable = false)
    private String userID;

    @Column(name = "Beschrijving", nullable = false)
    private String beschrijving;

    public Spottermeting(Spottermeting spottermeting)
    {
        this.id=spottermeting.id;
        this.userID = spottermeting.userID;
        this.beschrijving = spottermeting.beschrijving;

    }

    public Spottermeting(){
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

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }
}
