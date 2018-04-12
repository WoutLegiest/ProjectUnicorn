package be.kuleuven.gent.project.Support;

import java.util.ArrayList;

public class DataAdapter {
    private ArrayList<ArrayList>data;

    public DataAdapter(ArrayList<ArrayList> data) {
        this.data = data;
    }

    public ArrayList<ArrayList> getData() {
        return data;
    }

    public void setData(ArrayList<ArrayList> data) {
        this.data = data;
    }

    public DataAdapter() {
    }
}