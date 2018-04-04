package be.kuleuven.gent.project.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Entity
@Table(name="ProfessionalProject")
public class ProfessionalProject implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false, length=16)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = false)
    private String locationName;

    @Column(name = "latitude", nullable = false)
    private float latitude;

    @Column(name = "longitude", nullable = false)
    private float longitude;

    public ProfessionalProject(ProfessionalProject professionalProject) {
        this.name = professionalProject.name;
        this.locationName = professionalProject.locationName;
        this.id= professionalProject.id;
    }

    public ProfessionalProject(){
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String naam) {
        this.name = naam;
    }

    public String getLocatie() {
        return locationName;
    }

    public void setLocatie(String locatie) {
        locationName = locatie;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

}
