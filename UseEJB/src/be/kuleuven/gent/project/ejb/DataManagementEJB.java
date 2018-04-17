package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.Data;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

@Stateless
public class DataManagementEJB implements DataManagementEJBLocal {

    @PersistenceContext(unitName="unicorn")
    private EntityManager em;

    @Override
    public Data findData(long id) {
        return em.find(Data.class, id);
    }

    @Override
    public Data makeDataObject(ArrayList<ArrayList<Float>> input, ArrayList<ArrayList<Float>> results) {

        Data data = new Data();

        data.setxData(toByteArray(input.get(0)));
        data.setyData(toByteArray(input.get(1)));
        data.setzData(toByteArray(input.get(2)));

        data.setResult1x(toByteArray(results.get(0)));
        data.setResult2x(toByteArray(results.get(1)));
        data.setFreqx(toByteArray(results.get(2)));
        data.setResult1y(toByteArray(results.get(3)));
        data.setResult2y(toByteArray(results.get(4)));
        data.setFreqy(toByteArray(results.get(5)));
        data.setResult1z(toByteArray(results.get(6)));
        data.setResult2z(toByteArray(results.get(7)));
        data.setFreqz(toByteArray(results.get(8)));

        em.persist(data);
        em.flush();


        return data;
    }

    @Override
    public byte[] toByteArray(ArrayList<Float> dataList) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);
        for (float dataElement : dataList) {
            try {
                out.writeUTF(String.valueOf(dataElement));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return baos.toByteArray();
    }

    @Override
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
