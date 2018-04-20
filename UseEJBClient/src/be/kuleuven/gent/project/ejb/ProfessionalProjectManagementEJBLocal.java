package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.ProfessionalMeasurement;
import be.kuleuven.gent.project.data.ProfessionalProject;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProfessionalProjectManagementEJBLocal {

    List<ProfessionalProject> findAllProjects();

    ProfessionalProject findProject(long id);

    List<ProfessionalMeasurement> findAllMeasurements(Long projectId);

    List<String> projectByLocations(float latitude, float longitude);

    ProfessionalProject makeProject(String name, String location, float latitude, float longitude, String desc);

    <T> void updateDB(T t);

    void deleteProject(ProfessionalProject project);
}
