package be.kuleuven.gent.project.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

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

    @Column(name = "proUser_User_LoginName", nullable = false)
    private String loginUser;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date", nullable = false)
    private java.sql.Date date;

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

    @Column(table = "data", name="result1x")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]result1x;

    @Column(table = "data", name="result1y")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]result1y;

    @Column(table = "data", name="result1z")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]result1z;

    @Column(table = "data", name="result2x")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]result2x;

    @Column(table = "data", name="result2y")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]result2y;

    @Column(table = "data", name="result2z")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]result2z;

    @Column(table = "data", name="freqx")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]freqx;

    @Column(table = "data", name="freqy")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]freqy;

    @Column(table = "data", name="freqz")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]freqz;

    public ProfessionalMeasurement(ProfessionalMeasurement professionalMeasurement) {
        this.id=professionalMeasurement.id;
        this.loginUser = professionalMeasurement.loginUser;
        this.description = professionalMeasurement.description;
        this.idData = professionalMeasurement.idData;
        this.idProject = professionalMeasurement.idProject;
        this.date = professionalMeasurement.date;
        this.xData=professionalMeasurement.xData;
        this.yData=professionalMeasurement.yData;
        this.zData=professionalMeasurement.zData;
    }

    public ProfessionalMeasurement(int idProject, String loginUser, String description, byte[] xData, byte[] yData, byte[] zData) {
        this.idProject = idProject;
        this.loginUser = loginUser;
        this.description = description;
        this.xData = xData;
        this.yData = yData;
        this.zData = zData;
    }

    public ProfessionalMeasurement(int idProject, String loginUser, String description, java.sql.Date date, byte[] xData, byte[] yData, byte[] zData) {
        this.idProject = idProject;
        this.loginUser = loginUser;
        this.description = description;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
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

    public byte[] getResult1x() {
        return result1x;
    }

    public void setResult1x(byte[] result1x) {
        this.result1x = result1x;
    }

    public byte[] getResult1y() {
        return result1y;
    }

    public void setResult1y(byte[] result1y) {
        this.result1y = result1y;
    }

    public byte[] getResult1z() {
        return result1z;
    }

    public void setResult1z(byte[] result1z) {
        this.result1z = result1z;
    }

    public byte[] getResult2x() {
        return result2x;
    }

    public void setResult2x(byte[] result2x) {
        this.result2x = result2x;
    }

    public byte[] getResult2y() {
        return result2y;
    }

    public void setResult2y(byte[] result2y) {
        this.result2y = result2y;
    }

    public byte[] getResult2z() {
        return result2z;
    }

    public void setResult2z(byte[] result2z) {
        this.result2z = result2z;
    }

    public byte[] getFreqx() {
        return freqx;
    }

    public void setFreqx(byte[] freqx) {
        this.freqx = freqx;
    }

    public byte[] getFreqy() {
        return freqy;
    }

    public void setFreqy(byte[] freqy) {
        this.freqy = freqy;
    }

    public byte[] getFreqz() {
        return freqz;
    }

    public void setFreqz(byte[] freqz) {
        this.freqz = freqz;
    }
}
