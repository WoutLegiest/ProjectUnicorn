package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.ProfessionalMeasurement;

import javax.ejb.Local;
import java.io.IOException;
import java.util.ArrayList;

@Local
public interface ApplicationManagementEJBLocal {

    void addMeaserment(ArrayList<Float> x, ArrayList<Float> y, ArrayList<Float> z, int ProjectId, String UserId,
                       String description, ArrayList<ArrayList<Float>> result);

    byte[] toByteArray(ArrayList<Float> dataList);

    ArrayList<ArrayList<Float>> processData(ArrayList<Float> list) throws IOException;

    String maakScript(ArrayList<Float> list) throws IOException;

}
