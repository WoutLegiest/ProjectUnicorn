package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.STEMMeasurement;
import be.kuleuven.gent.project.data.StemProject;
import be.kuleuven.gent.project.data.Teacher;
import be.kuleuven.gent.project.data.User;

import javax.ejb.Local;
import java.util.List;

/**
 * Interface waarmee we een StemProjectManagementEJB kunnen aanmaken
 */
@Local
public interface StemProjectManagementEJBLocal {

    void createProject(StemProject sp, int aantal);

    List<StemProject> findAllProjectsByTeacher(Teacher t);

    List<STEMMeasurement> findAllMeasurementsByStudent(User u);

    StemProject findProjectByUser(User user);

    StemProject findProject(long id);

    <T> void updateDB(T t);

}
