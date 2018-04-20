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
public class ProfessionalProjectManagementEJB implements ProfessionalProjectManagementEJBLocal {

    @PersistenceContext(unitName="unicorn")
    private EntityManager em;

    /**
     * Default constructor.
     */
    public ProfessionalProjectManagementEJB() {
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
    public ProfessionalProject makeProject(String name, String location, float latitude, float longitude, String desc){

        ProfessionalProject project = new ProfessionalProject(name, location,latitude,longitude,desc);
        em.persist(project);

        return project;
    }

    @Override
    public <T> void updateDB(T t) {
        em.merge(t);
    }

    @Override
    public void deleteProject(ProfessionalProject project) {
        List<ProfessionalMeasurement> measurementsOfProject = findAllMeasurements(project.getId());

        for (ProfessionalMeasurement measurement : measurementsOfProject) {
            ProfessionalMeasurement detachedMeasurement = em.merge(measurement);
            em.remove(detachedMeasurement);
        }

        //https://stackoverflow.com/questions/7218715/delete-database-rows-with-ejb-3-0
        ProfessionalProject detachedProject = em.merge(project);
        em.remove(detachedProject);
    }


}