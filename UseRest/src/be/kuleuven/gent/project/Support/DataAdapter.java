package be.kuleuven.gent.project.Support;

import java.util.ArrayList;

public class DataAdapter {

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

    public DataAdapter(ArrayList<Float> xR1, ArrayList<Float> xR2, ArrayList<Float> xF, ArrayList<Float> yR1, ArrayList<Float> yR2, ArrayList<Float> yF, ArrayList<Float> zR1, ArrayList<Float> zR2, ArrayList<Float> zF) {
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
}