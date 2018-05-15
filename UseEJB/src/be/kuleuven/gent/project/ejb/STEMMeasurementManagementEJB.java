package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Date;

@Stateless
public class STEMMeasurementManagementEJB implements STEMMeasurementManagementEJBLocal {

    @PersistenceContext(unitName="unicorn")
    private EntityManager em;

    @Override
    public STEMMeasurement makeMeasurement(Student student, long idData, String description, Date date) {

        STEMMeasurement stemMeasurement = new STEMMeasurement(0l,idData, student.getUser().getLogin(), description,date);

        em.persist(stemMeasurement);
        return stemMeasurement;
    }
}
