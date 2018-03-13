package be.kuleuven.gent.project.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
import javax.persistence.TypedQuery;

import be.kuleuven.gent.project.data.SpotterProject;

@Stateless
public class SpotterProjectManagementEJB implements SpotterProjectManagementEJBLocal{

    @PersistenceContext(unitName="unicorn")
    private EntityManager em;

    /**
     * Default constructor.
     */
    public SpotterProjectManagementEJB() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public List<SpotterProject> findAllProjects() {
        TypedQuery<SpotterProject> q =
                em.createQuery("SELECT p FROM SpotterProject p", SpotterProject.class);
        List<SpotterProject> projects = q.getResultList();
        return projects;
    }

    @Override
    public SpotterProject findProject(long id){
        return em.find(SpotterProject.class, id);
    }
}