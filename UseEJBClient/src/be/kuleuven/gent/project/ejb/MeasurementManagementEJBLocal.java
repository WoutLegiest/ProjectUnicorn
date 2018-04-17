package be.kuleuven.gent.project.ejb;

import javax.ejb.Local;
import java.io.IOException;
import java.util.ArrayList;

@Local
public interface MeasurementManagementEJBLocal {

    ArrayList<ArrayList<Float>> processData(ArrayList<Float> list) throws IOException;

    String maakScript(ArrayList<Float> list) throws IOException;


}
