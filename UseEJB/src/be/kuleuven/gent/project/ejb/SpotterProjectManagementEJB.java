package be.kuleuven.gent.project.ejb;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
import javax.persistence.TypedQuery;

import be.kuleuven.gent.project.data.SpotterProject;
import org.apache.tools.ant.Project;

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

    @Override
    public List<String> projectByLocations(double latitude, double longitude) {

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT p FROM SpotterProject p WHERE Longitude >");
        queryBuilder.append(longitude-1);
        queryBuilder.append(" AND ");
        queryBuilder.append(" Longitude < ");
        queryBuilder.append(longitude+1);
        queryBuilder.append(" AND ");
        queryBuilder.append(" Latitude > ");
        queryBuilder.append(latitude-1);
        queryBuilder.append(" AND ");
        queryBuilder.append(" Latitude < ");
        queryBuilder.append(latitude+1);
        queryBuilder.append(";");

        TypedQuery<SpotterProject> q =
                em.createQuery(queryBuilder.toString(), SpotterProject.class);
        List<SpotterProject> projects = q.getResultList();
        List<String> output = new LinkedList<>();
        for (SpotterProject p : projects){
            output.add(p.getLocatie());
        }
        return output;
    }
}