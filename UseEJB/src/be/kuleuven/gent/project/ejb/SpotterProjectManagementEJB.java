package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.ProfessionalMeasurement;
import be.kuleuven.gent.project.data.ProfessionalProject;

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
    public List<ProfessionalProject> findAllProjects() {
        TypedQuery<ProfessionalProject> q =
                em.createQuery("SELECT p FROM ProfessionalProject p", ProfessionalProject.class);
        return q.getResultList();
    }

    @Override
    public ProfessionalProject findProject(long id){
        return em.find(ProfessionalProject.class, id);
    }

    @Override
    public List<ProfessionalMeasurement> findAllMeasurements(Long projectId){

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT p FROM ProfessionalMeasurement p ");
        queryBuilder.append("WHERE p.professionalProject.id = ");
        queryBuilder.append(projectId);

        TypedQuery<ProfessionalMeasurement> q = em.createQuery(queryBuilder.toString(), ProfessionalMeasurement.class);

        return q.getResultList();
    }

    @Override
    public List<String> projectByLocations(float latitude, float longitude) {

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT p FROM ProfessionalProject p ");
        queryBuilder.append("WHERE p.latitude BETWEEN ");
        queryBuilder.append(latitude-1);
        queryBuilder.append(" AND ");
        queryBuilder.append(latitude+1);
        queryBuilder.append(" AND ");
        queryBuilder.append(" p.longitude BETWEEN ");
        queryBuilder.append(longitude-1);
        queryBuilder.append(" AND ");
        queryBuilder.append(longitude+1);

        TypedQuery<ProfessionalProject> q =
                em.createQuery(queryBuilder.toString(), ProfessionalProject.class);
        List<ProfessionalProject> projects = q.getResultList();
        List<String> output = new LinkedList<>();
        for (ProfessionalProject p : projects){
            output.add(p.getName());
        }
        return output;
    }

    @Override
    public ProfessionalProject makeProject(String name,float latitude, float longitude, String location) {
        ProfessionalProject professionalProject = new ProfessionalProject(name, latitude,longitude,location);
        em.persist(professionalProject);
        return professionalProject;
    }


}