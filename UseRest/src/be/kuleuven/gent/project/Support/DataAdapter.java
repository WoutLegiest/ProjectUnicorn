package be.kuleuven.gent.project.Support;

import be.kuleuven.gent.project.data.Data;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Een Adapter om de data die binnenkomt van de applicatie op een goede manier te werken.
 */
public class DataAdapter {

    private long id;

    private ArrayList<Float>xData;
    private ArrayList<Float>yData;
    private ArrayList<Float>zData;

    private ArrayList<Float>xR1;
    private ArrayList<Float>xR2;
    private ArrayList<Float>xF;
    private ArrayList<Float>yR1;
    private ArrayList<Float>yR2;
    private ArrayList<Float>yF;
    private ArrayList<Float>zR1;
    private ArrayList<Float>zR2;
    private ArrayList<Float>zF;
    

    public DataAdapter() {
    }

    public DataAdapter(long id,ArrayList<Float> xR1, ArrayList<Float> xR2, ArrayList<Float> xF, ArrayList<Float> yR1, ArrayList<Float> yR2, ArrayList<Float> yF, ArrayList<Float> zR1, ArrayList<Float> zR2, ArrayList<Float> zF) {
        this.id=id;
        this.xR1 = xR1;
        this.xR2 = xR2;
        this.xF = xF;
        this.yR1 = yR1;
        this.yR2 = yR2;
        this.yF = yF;
        this.zR1 = zR1;
        this.zR2 = zR2;
        this.zF = zF;
    }

    public DataAdapter(long id, ArrayList<ArrayList<Float>> input){

        this.id=id;
        this.xR1=input.get(0);
        this.xR2=input.get(1);
        this.xF=input.get(2);
        this.yR1=input.get(3);
        this.yR2=input.get(4);
        this.yF=input.get(5);
        this.zR1=input.get(6);
        this.zR2=input.get(7);
        this.zF=input.get(8);
    }

    public DataAdapter(long id, Data data){
        this.id=id;
        this.xData=toArrayList(data.getxData());
        this.yData=toArrayList(data.getyData());
        this.zData=toArrayList(data.getzData());
        this.xR1=toArrayList(data.getResult1x());
        this.xR2=toArrayList(data.getResult2x());
        this.xF=toArrayList(data.getFreqx());
        this.yR1=toArrayList(data.getResult1y());
        this.yR2=toArrayList(data.getResult2y());
        this.yF=toArrayList(data.getFreqy());
        this.zR1=toArrayList(data.getResult1z());
        this.zR2=toArrayList(data.getResult2z());
        this.zF=toArrayList(data.getFreqz());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ArrayList<Float> getxData() {
        return xData;
    }

    public void setxData(ArrayList<Float> xData) {
        this.xData = xData;
    }

    public ArrayList<Float> getyData() {
        return yData;
    }

    public void setyData(ArrayList<Float> yData) {
        this.yData = yData;
    }

    public ArrayList<Float> getzData() {
        return zData;
    }

    public void setzData(ArrayList<Float> zData) {
        this.zData = zData;
    }

    public ArrayList<Float> getxR1() {
        return xR1;
    }

    public void setxR1(ArrayList<Float> xR1) {
        this.xR1 = xR1;
    }

    public ArrayList<Float> getxR2() {
        return xR2;
    }

    public void setxR2(ArrayList<Float> xR2) {
        this.xR2 = xR2;
    }

    public ArrayList<Float> getxF() {
        return xF;
    }

    public void setxF(ArrayList<Float> xF) {
        this.xF = xF;
    }

    public ArrayList<Float> getyR1() {
        return yR1;
    }

    public void setyR1(ArrayList<Float> yR1) {
        this.yR1 = yR1;
    }

    public ArrayList<Float> getyR2() {
        return yR2;
    }

    public void setyR2(ArrayList<Float> yR2) {
        this.yR2 = yR2;
    }

    public ArrayList<Float> getyF() {
        return yF;
    }

    public void setyF(ArrayList<Float> yF) {
        this.yF = yF;
    }

    public ArrayList<Float> getzR1() {
        return zR1;
    }

    public void setzR1(ArrayList<Float> zR1) {
        this.zR1 = zR1;
    }

    public ArrayList<Float> getzR2() {
        return zR2;
    }

    public void setzR2(ArrayList<Float> zR2) {
        this.zR2 = zR2;
    }

    public ArrayList<Float> getzF() {
        return zF;
    }

    public void setzF(ArrayList<Float> zF) {
        this.zF = zF;
    }

    public ArrayList<Float> toArrayList(byte[] dataByteArray)  {
        ByteArrayInputStream bais = new ByteArrayInputStream(dataByteArray);
        DataInputStream in = new DataInputStream(bais);
        ArrayList<Float> data = new ArrayList<>();
        try {
            while (in.available() > 0) {
                String element = in.readUTF();
                data.add(Float.parseFloat(element));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.reverse(data);
        return data;
    }
}