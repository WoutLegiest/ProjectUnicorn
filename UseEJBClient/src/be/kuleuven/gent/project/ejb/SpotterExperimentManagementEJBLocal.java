package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.SpotterProject;
import be.kuleuven.gent.project.data.Spottermeting;

import javax.ejb.Local;
import java.util.LinkedList;
import java.util.List;

@Local
public interface SpotterExperimentManagementEJBLocal {

    public boolean experimentVerwerken(List<LinkedList<Double>> input );

    List<Spottermeting> findAllMetingen(SpotterProject spotterProject);
}
