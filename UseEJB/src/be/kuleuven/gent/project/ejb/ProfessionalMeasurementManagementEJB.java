package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class ProfessionalMeasurementManagementEJB implements ProfessionalMeasurementManagementEJBLocal {

    @PersistenceContext(unitName="unicorn")
    private EntityManager em;

    public ProfessionalMeasurementManagementEJB() {
        //default constructor
    }

    @Override
    public List<ProfessionalMeasurement> findAllMeasurementsByProject(ProfessionalProject professionalProject) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT m FROM ProfessionalMeasurement m ");
        query.append("WHERE m.professionalProject.id = ");
        query.append(professionalProject.getId());

        TypedQuery<ProfessionalMeasurement> q = em.createQuery(query.toString() , ProfessionalMeasurement.class);

        return q.getResultList();
    }

    @Override
    public List<ProfessionalMeasurement> findAllMeasurementsByUser(User user) {

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT p FROM ProfessionalMeasurement p ");
        queryBuilder.append("WHERE p.proUser.user.login = '");
        queryBuilder.append(user.getLogin());
        queryBuilder.append("'");

        TypedQuery<ProfessionalMeasurement> q = em.createQuery(queryBuilder.toString(), ProfessionalMeasurement.class);

        return q.getResultList();
    }

    @Override
    public ProfessionalMeasurement makeMeasurement(ProUser proUser, ProfessionalProject professionalProject, Data data, String description, java.sql.Date date) {

        ProfessionalMeasurement professionalMeasurement = new ProfessionalMeasurement(data, professionalProject, proUser, description, date);

        em.persist(professionalMeasurement);

        return professionalMeasurement;
    }

    @Override
    public ProfessionalMeasurement findMeasurementById(Long id){
        StringBuilder query = new StringBuilder();
        query.append("SELECT m FROM ProfessionalMeasurement m WHERE m.id = ");
        query.append(id);
        TypedQuery<ProfessionalMeasurement> q =
                em.createQuery(query.toString() , ProfessionalMeasurement.class);

        if (q.getResultList().size() != 1) return null;
        return q.getResultList().get(0);
    }

    @Override
    public <T> void updateDB(T t) {
        em.merge(t);
    }

}
