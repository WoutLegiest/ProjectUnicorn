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

    @Id
    @ManyToOne
    @JoinColumn(name = "professionalProjectId" , nullable = false)
    private ProfessionalProject professionalProject;

    @Id
    @OneToOne
    @JoinColumn(name = "loginName", nullable = false)
    private ProUser proUser;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date", nullable = false)
    private java.sql.Date date;

    public ProfessionalMeasurement(){
        super();
    }

    public ProfessionalMeasurement(ProfessionalMeasurement professionalMeasurement) {
        this.id=professionalMeasurement.id;
        this.proUser = professionalMeasurement.proUser;
        this.description = professionalMeasurement.description;
        this.data = professionalMeasurement.data;
        this.professionalProject = professionalMeasurement.professionalProject;
        this.date = professionalMeasurement.date;
    }

    public ProfessionalMeasurement(Data data, ProfessionalProject professionalProject, ProUser proUser, String description, java.sql.Date date) {
        this.data = data;
        this.professionalProject = professionalProject;
        this.proUser = proUser;
        this.description = description;
        this.date = date;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProUser getProUser() {
        return proUser;
    }

    public void setProUser(ProUser proUser) {
        this.proUser = proUser;
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

    public ProfessionalProject getProfessionalProject() {
        return professionalProject;
    }

    public void setProfessionalProject(ProfessionalProject professionalProject) {
        this.professionalProject = professionalProject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }
}
