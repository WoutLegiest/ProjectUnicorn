package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.SpotterProject;
import be.kuleuven.gent.project.data.Spottermeting;

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
    public boolean experimentVerwerken(List<LinkedList<Double>> input) {

        return false;
    }

    @Override
    public List<Spottermeting> findAllMetingen(SpotterProject spotterProject) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT m FROM Spottermeting m WHERE m.id = ");
        query.append(spotterProject.getId());
        TypedQuery<Spottermeting> q =
                em.createQuery(query.toString() , Spottermeting.class);
        return q.getResultList();
    }
}
