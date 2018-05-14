package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.STEMMeasurement;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Stateless
public class StemMeasurementManagementEJB implements StemMeasurementManagementEJBLocal {

    @PersistenceContext(unitName="unicorn")
    private EntityManager em;

    public StemMeasurementManagementEJB() {
    }

    @Override
    public STEMMeasurement findMeasurementById(Long id){
        StringBuilder query = new StringBuilder();
        query.append("SELECT m FROM StemMeasurement m WHERE m.id = ");
        query.append(id);
        TypedQuery<STEMMeasurement> q =
                em.createQuery(query.toString() , STEMMeasurement.class);

        if (q.getResultList().size() != 1) return null;
        return q.getResultList().get(0);
    }
}
