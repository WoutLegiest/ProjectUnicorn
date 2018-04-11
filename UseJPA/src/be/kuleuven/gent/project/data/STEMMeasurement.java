package be.kuleuven.gent.project.data;

import com.sun.xml.xsom.impl.scd.Iterators;

import javax.persistence.*;
import javax.websocket.Encoder;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;

@XmlRootElement
@Entity
@Table(name="STEMMeasurement")
@SecondaryTable(name = "data", pkJoinColumns=@PrimaryKeyJoinColumn(name="idData", referencedColumnName="idData"))
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


    @Column(table = "data", name="xData")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private Byte[]xData;

    @Column(table = "data", name="yData")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private Byte[]yData;

    @Column(table = "data", name="zData")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private Byte[]zData;

    public Byte[] getxData() {
        return xData;
    }

    public STEMMeasurement(Long idProject, Long idData, String userLogin, Byte[] xData, Byte[] yData, Byte[] zData) {
        this.idProject = idProject;
        this.idData = idData;
        this.userLogin = userLogin;
        this.xData = xData;
        this.yData = yData;
        this.zData = zData;
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

    public void setxData(Byte[] xData) {
        this.xData = xData;
    }

    public Byte[] getyData() {
        return yData;
    }

    public void setyData(Byte[] yData) {
        this.yData = yData;
    }

    public Byte[] getzData() {
        return zData;
    }

    public void setzData(Byte[] zData) {
        this.zData = zData;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
