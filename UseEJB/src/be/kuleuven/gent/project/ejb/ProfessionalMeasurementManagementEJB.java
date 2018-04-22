package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * EJB die zorgt voor het beheren en zoeken van de Professionele Metingen
 */
@Stateless
public class ProfessionalMeasurementManagementEJB implements ProfessionalMeasurementManagementEJBLocal {

    @PersistenceContext(unitName="unicorn")
    private EntityManager em;

    /**
     * Default constructor
     */
    public ProfessionalMeasurementManagementEJB() {
        //default constructor
    }

    /**
     * All metingen die horen bij een bepaald project worden hier opgehaald uit de databank
     * @param professionalProject Het betreffende project waarvan we de metingen willen ophalen
     * @return Een lijst van professionele metingen
     */
    @Override
    public List<ProfessionalMeasurement> findAllMeasurementsByProject(ProfessionalProject professionalProject) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT m FROM ProfessionalMeasurement m ");
        query.append("WHERE m.professionalProject.id = ");
        query.append(professionalProject.getId());

        TypedQuery<ProfessionalMeasurement> q = em.createQuery(query.toString() , ProfessionalMeasurement.class);

        return q.getResultList();
    }

    /**
     * Alle metingen van een bepaalde gebruiker wordt gezocht
     * @param user De betreffende gebruiker
     * @return De lijst van professionele metingen
     */
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

    /**
     * Een methode die een profesionele meting gaat aanmaken en deze op de databank zal plaatsten
     * @param proUser De gebruiker die de meting uit heeft gevoerd
     * @param professionalProject Het gekoppelde professionele project
     * @param data De data van de meting zelf
     * @param description Een omschrijving
     * @param date De datum van de meting
     * @return Het gemaakte object wordt teruggegeven
     */
    @Override
    public ProfessionalMeasurement makeMeasurement(ProUser proUser, ProfessionalProject professionalProject, Data data, String description, java.sql.Date date) {

        ProfessionalMeasurement professionalMeasurement = new ProfessionalMeasurement(data, professionalProject, proUser, description, date);

        em.persist(professionalMeasurement);

        return professionalMeasurement;
    }

    /**
     * Er wordt een meting gezocht op basis van zijn ID
     * @param id Een long
     * @return De meting met die ID, indien niet beschikbaar null
     */
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

    /**
     * Een generische methode om de objecten bij te werken
     * @param t
     * @param <T>
     */
    @Override
    public <T> void updateDB(T t) {
        em.merge(t);
    }

    /**
     * Methode om metingen te verwijderen uit de databank
     * @param measurement het meting object dat we willen verwijderen
     */
    @Override
    public void deleteMeasurement(ProfessionalMeasurement measurement){
        ProfessionalMeasurement detachedMeasurement = em.merge(measurement);
        em.remove(detachedMeasurement);
    }

}
