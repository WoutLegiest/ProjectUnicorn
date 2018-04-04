package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.ProfessionalMeasurement;
import be.kuleuven.gent.project.data.ProfessionalProject;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

public class SpotterExperimentManagementEJB implements SpotterExperimentManagementEJBLocal {

    @PersistenceContext(unitName="unicorn")
    private EntityManager em;

    public SpotterExperimentManagementEJB() {
        //default constructor
    }

    @Override
    public boolean processExperiment(List<LinkedList<Double>> input) {

        return false;
    }

    @Override
    public List<ProfessionalMeasurement> findAllMeasurements(ProfessionalProject professionalProject) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT m FROM ProfessionalMeasurement m WHERE m.id = ");
        query.append(professionalProject.getId());
        TypedQuery<ProfessionalMeasurement> q =
                em.createQuery(query.toString() , ProfessionalMeasurement.class);
        return q.getResultList();
    }
}
