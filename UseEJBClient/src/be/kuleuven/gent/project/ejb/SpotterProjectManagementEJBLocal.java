package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.ProfessionalMeasurement;
import be.kuleuven.gent.project.data.ProfessionalProject;

import javax.ejb.Local;
import javax.ws.rs.core.Response;
import java.util.List;

@Local
public interface SpotterProjectManagementEJBLocal {

    List<ProfessionalProject> findAllProjects();

    ProfessionalProject findProject(long id);

    List<ProfessionalMeasurement> findAllMeasurements(Long projectId);

    List<String> projectByLocations(float latitude, float longitude);

    ProfessionalProject makeProject(String name,float latitude, float longitude, String location);

}
