package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.ProfessionalProject;
import be.kuleuven.gent.project.data.ProfessionalMeasurement;
import be.kuleuven.gent.project.data.User;
import com.sun.xml.xsom.impl.scd.Iterators;

import javax.ejb.Local;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Local
public interface ProfessionalMeasurementManagementEJBLocal {

    public boolean processExperiment(List<LinkedList<Double>> input );

    List<ProfessionalMeasurement> findAllMeasurementsByProject(ProfessionalProject professionalProject);

    List<ProfessionalMeasurement> findAllMeasurementsByUser(User user);

    public ProfessionalMeasurement makeMeasurement(int idProject, String idUser, String description, ArrayList<Float> xData, ArrayList<Float>yData, ArrayList<Float>zData);

    public byte[] toByteArray(ArrayList<Float> dataList);

    public ArrayList<Float> toArrayList(byte[] dataByteArray);
}
