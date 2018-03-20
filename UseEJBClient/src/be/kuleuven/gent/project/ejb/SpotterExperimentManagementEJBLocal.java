package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.SpotterProject;

import javax.ejb.Local;
import java.util.LinkedList;
import java.util.List;

@Local
public interface SpotterExperimentManagementEJBLocal {

    public boolean experimentVerwerken(LinkedList<LinkedList<Double>> input );


}
