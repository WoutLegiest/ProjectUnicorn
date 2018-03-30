package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.SpotterProject;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class SpotterProjectManagementEJB implements SpotterProjectManagementEJBLocal{

    @PersistenceContext(unitName="unicorn")
    private EntityManager em;

    /**
     * Default constructor.
     */
    public SpotterProjectManagementEJB() {
        //Auto-generated constructor stub
    }

    @Override
    public List<SpotterProject> findAllProjects() {
        TypedQuery<SpotterProject> q =
                em.createQuery("SELECT p FROM SpotterProject p", SpotterProject.class);
        return q.getResultList();
    }


    @Override
    public SpotterProject findProject(long id){
        return em.find(SpotterProject.class, id);
    }

    @Override
    public List<String> projectByLocations(float latitude, float longitude) {

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT p FROM SpotterProject p ");
        queryBuilder.append("WHERE p.latitude BETWEEN ");
        queryBuilder.append(latitude-1);
        queryBuilder.append(" AND ");
        queryBuilder.append(latitude+1);
        queryBuilder.append(" AND ");
        queryBuilder.append(" p.longitude BETWEEN ");
        queryBuilder.append(longitude-1);
        queryBuilder.append(" AND ");
        queryBuilder.append(longitude+1);

        TypedQuery<SpotterProject> q =
                em.createQuery(queryBuilder.toString(), SpotterProject.class);
        List<SpotterProject> projects = q.getResultList();
        List<String> output = new LinkedList<>();
        for (SpotterProject p : projects){
            output.add(p.getNaam());
        }
        return output;
    }
}