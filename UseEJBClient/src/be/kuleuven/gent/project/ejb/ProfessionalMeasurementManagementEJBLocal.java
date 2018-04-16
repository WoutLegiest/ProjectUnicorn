package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.*;
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

    ProfessionalMeasurement makeMeasurement(ProUser proUser, ProfessionalProject professionalProject, Data data, String description, java.sql.Date date);

    ProfessionalProject makeProject(String name, String location, float latitude, float longitude, String desc);

    ProfessionalMeasurement findMeasurementById(Long id);

    byte[] toByteArray(ArrayList<Float> dataList);

    ArrayList<Float> toArrayList(byte[] dataByteArray);
}
