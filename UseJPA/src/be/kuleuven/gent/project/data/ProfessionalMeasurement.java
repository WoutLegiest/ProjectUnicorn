package be.kuleuven.gent.project.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Entity
@Table(name="ProfessionalMeasurement")
@SecondaryTable(name = "data", pkJoinColumns=@PrimaryKeyJoinColumn(name="idData", referencedColumnName="idData"))
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

    @Column(table = "data", name="xData")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]xData;

    @Column(table = "data", name="yData")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]yData;

    @Column(table = "data", name="zData")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]zData;

    public ProfessionalMeasurement(ProfessionalMeasurement professionalMeasurement) {
        this.id=professionalMeasurement.id;
        this.idUser = professionalMeasurement.idUser;
        this.description = professionalMeasurement.description;
        this.idData = professionalMeasurement.idData;
        this.idProject = professionalMeasurement.idProject;
        this.xData=professionalMeasurement.xData;
        this.yData=professionalMeasurement.yData;
        this.zData=professionalMeasurement.zData;
    }

    public ProfessionalMeasurement(int idProject, String idUser, String description, byte[] xData, byte[] yData, byte[] zData) {
        this.idProject = idProject;
        this.idUser = idUser;
        this.description = description;
        this.xData = xData;
        this.yData = yData;
        this.zData = zData;
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

    public byte[] getxData() {
        return xData;
    }

    public void setxData(byte[] xData) {
        this.xData = xData;
    }

    public byte[] getyData() {
        return yData;
    }

    public void setyData(byte[] yData) {
        this.yData = yData;
    }

    public byte[] getzData() {
        return zData;
    }

    public void setzData(byte[] zData) {
        this.zData = zData;
    }
}
