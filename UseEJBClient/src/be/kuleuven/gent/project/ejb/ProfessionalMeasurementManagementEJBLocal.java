package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.*;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProfessionalMeasurementManagementEJBLocal {

    List<ProfessionalMeasurement> findAllMeasurementsByProject(ProfessionalProject professionalProject);

    List<ProfessionalMeasurement> findAllMeasurementsByUser(User user);

    ProfessionalMeasurement makeMeasurement(ProUser proUser, ProfessionalProject professionalProject, Data data, String description, java.sql.Date date);

    ProfessionalMeasurement findMeasurementById(Long id);

    <T> void updateDB(T t);
}
