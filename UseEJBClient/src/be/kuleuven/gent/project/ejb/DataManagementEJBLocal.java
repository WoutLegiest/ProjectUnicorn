package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.Data;

import javax.ejb.Local;
import java.util.ArrayList;

/**
 * Interface waarmee we een DataManagementEJB kunnen aanmaken
 */
@Local
public interface DataManagementEJBLocal {

    Data findData(long id);

    Data makeDataObject(ArrayList<ArrayList<Float>> input, ArrayList<ArrayList<Float>> results);

    byte[] toByteArray(ArrayList<Float> dataList);

    ArrayList<Float> toArrayList(byte[] dataByteArray);
}
