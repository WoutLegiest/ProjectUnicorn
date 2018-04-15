package be.kuleuven.gent.project.data;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
@Entity
@Table(name="Data")
public class Data implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="IdData", nullable = false)
    private Long id;

    @Column(name="xData", nullable = false)
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]xData;

    @Column(name="yData", nullable = false)
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]yData;

    @Column(name="zData", nullable = false)
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]zData;

    @Column(name="result1x")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]result1x;

    @Column(name="result1y")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]result1y;

    @Column(name="result1z")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]result1z;

    @Column(name="result2x")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]result2x;

    @Column(name="result2y")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]result2y;

    @Column(name="result2z")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]result2z;

    @Column(name="freqx")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]freqx;

    @Column(name="freqy")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]freqy;

    @Column(name="freqz")
    @Lob
    @Basic(fetch=FetchType.LAZY)
    private byte[]freqz;

    public Data() {
        super();
    }

    public Data(Data data) {
        this.id = data.id;
        this.xData = data.xData;
        this.yData = data.yData;
        this.zData = data.zData;
        this.result1x = data.result1x;
        this.result1y = data.result1y;
        this.result1z = data.result1z;
        this.result2x = data.result2x;
        this.result2y = data.result2y;
        this.result2z = data.result2z;
        this.freqx = data.freqx;
        this.freqy = data.freqy;
        this.freqz = data.freqz;
    }

    public Data(byte[] xData, byte[] yData, byte[] zData) {
        this.xData = xData;
        this.yData = yData;
        this.zData = zData;
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

    public Long getId() {
        return id;
    }
}
