package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.StemProject;
import be.kuleuven.gent.project.data.Teacher;

import javax.ejb.Local;
import java.util.List;

@Local
public interface StemProjectManagementEJBLocal {

    void createProject(StemProject sp, int aantal);

    List<StemProject> findAllProjectsByTeacher(Teacher t);

}
