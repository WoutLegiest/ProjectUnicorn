package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.ProfessionalProject;
import be.kuleuven.gent.project.data.ProfessionalMeasurement;

import javax.ejb.Local;
import java.util.LinkedList;
import java.util.List;

@Local
public interface SpotterExperimentManagementEJBLocal {

    public boolean processExperiment(List<LinkedList<Double>> input );

    List<ProfessionalMeasurement> findAllMeasurements(ProfessionalProject professionalProject);
}
