package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.SpotterProject;

import javax.ejb.Local;
import java.util.List;

@Local
public interface SpotterProjectManagementEJBLocal {

    public List<SpotterProject> findProjects();

}
