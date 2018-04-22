package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.ProfessionalMeasurement;
import be.kuleuven.gent.project.data.ProfessionalProject;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * EJB voor het beheren van een professioneel project
 */
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

    /**
     * Alle projecten worden opgehaald uit de databank
     * @return een lijst met alle projecten
     */
    @Override
    public List<ProfessionalProject> findAllProjects() {
        TypedQuery<ProfessionalProject> q =
                em.createQuery("SELECT p FROM ProfessionalProject p", ProfessionalProject.class);
        return q.getResultList();
    }

    /**
     * Een project vinden op basis van zijn ID
     * @param id De long ID
     * @return Het betreffende project
     */
    @Override
    public ProfessionalProject findProject(long id){
        return em.find(ProfessionalProject.class, id);
    }

    /**
     * Alle metingen van een project worden gezocht op basis van een projectID
     * @param projectId De long ID
     * @return een lijst van alle professionele metingen die bij dat project horen
     */
    @Override
    public List<ProfessionalMeasurement> findAllMeasurements(Long projectId){

        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT p FROM ProfessionalMeasurement p ");
        queryBuilder.append("WHERE p.professionalProject.id = ");
        queryBuilder.append(projectId);

        TypedQuery<ProfessionalMeasurement> q = em.createQuery(queryBuilder.toString(), ProfessionalMeasurement.class);

        return q.getResultList();
    }

    /**
     * Een project wordt gezocht op basis van zijn ligging
     * @param latitude De breedtegraad van het project
     * @param longitude De lengtegraad van het project
     * @return Een lijst met daarin de namen van de projecten die er dichtbij liggen
     */
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

    /**
     * Een nieuw project object aanmaken en toevoegen aan de database
     * @param name Naam van het project
     * @param location beschrijving van de locatie
     * @param latitude De breedtegraad
     * @param longitude De lengtegraad
     * @param desc beschrijving van het project
     * @return het gemaakte project object
     */
    @Override
    public ProfessionalProject makeProject(String name, String location, float latitude, float longitude, String desc){

        ProfessionalProject project = new ProfessionalProject(name, location,latitude,longitude,desc);
        em.persist(project);

        return project;
    }

    /**
     * Methode op project objecten een te pakken in de database
     * @param t
     * @param <T>
     */
    @Override
    public <T> void updateDB(T t) {
        em.merge(t);
    }

    /**
     * Voor het verwijderen van een professioneel project
     * @param project het te verwijderen project
     */
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