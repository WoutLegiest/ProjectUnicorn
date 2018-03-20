package be.kuleuven.gent.project.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="SpotterProject")
public class SpotterProject implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false, length=16)
    private Long id;

    @Column(name = "Naam", nullable = false)
    private String naam;

    @Column(name = "Location", nullable = false)
    private String LocatieNaam;

    @Column(name = "Latitude", nullable = false)
    private String latitude;

    @Column(name = "Longitude", nullable = false)
    private String longitude;

    public SpotterProject(SpotterProject spotterProject) {
        this.naam = spotterProject.naam;
        this.LocatieNaam = spotterProject.LocatieNaam;
        this.id=spotterProject.id;
    }

    public SpotterProject(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getLocatie() {
        return LocatieNaam;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLocatie(String locatie) {
        LocatieNaam = locatie;
    }
}
