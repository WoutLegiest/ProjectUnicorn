package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.Data;
import be.kuleuven.gent.project.data.ProfessionalProject;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DataManagementEJB implements DataManagementEJBLocal {

    @PersistenceContext(unitName="unicorn")
    private EntityManager em;

    @Override
    public Data findData(long id) {
        return em.find(Data.class, id);
    }
}
