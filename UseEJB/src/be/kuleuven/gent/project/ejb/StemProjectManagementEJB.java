package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.STEMMeasurement;
import be.kuleuven.gent.project.data.StemProject;
import be.kuleuven.gent.project.data.Teacher;
import be.kuleuven.gent.project.data.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * EJB om StemProjecten te beheren
 */
@Stateless
public class StemProjectManagementEJB implements StemProjectManagementEJBLocal {

    //Methode om de verschillende studetent groepen te creeen, automatisch en deze dan te weergeven op de teacher pagina

    @PersistenceContext(unitName = "unicorn")
    private EntityManager em;

    @Inject
    private UserManagementEJBLocal userEJB;

    /**
     * Default constructor
     */
    public StemProjectManagementEJB() {
    }

    /**
     * Er worden een aantal gebruikers aangemaakt voor indivduele projecten.
     * Deze worden op de database gezet.
     *
     * @param stemProject
     * @param amountOfGroups Het aantal groepjes
     */
    @Override
    public void createProject(StemProject stemProject, int amountOfGroups) {

        String school = stemProject.getTeacher().getSchool();
        String email = stemProject.getTeacher().getUser().getEmail();
        String studentClass = stemProject.getClassName();

        for (int i = 0; i < amountOfGroups; i++) {

            StringBuilder sb = new StringBuilder();

            sb.append(school);
            sb.append(".student.");
            sb.append(studentClass);
            sb.append(".");
            sb.append(String.valueOf(i + 1));


            User user = new User(sb.toString(), sb.toString(), "student", email);

            userEJB.createUser(user);
            userEJB.createStudent(user, school, studentClass, i);
        }

        updateDB(stemProject);

    }

    /**
     * Alle projecten worden gezocht van een bepaalde leerkracht
     *
     * @param t Het leerkracht object
     * @return De lijst met al de StemProjecten die hij/zij opvolgd
     */
    @Override
    public List<StemProject> findAllProjectsByTeacher(Teacher t) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT m FROM StemProject m WHERE m.teacher.user.login = '");
        query.append(t.getUser().getLogin());
        query.append("'");
        TypedQuery<StemProject> q = em.createQuery(query.toString(), StemProject.class);
        List<StemProject> stemProjects = q.getResultList();
       return stemProjects;
    }

    /**
     * Er worden metingen gezocht op basis van de student
     *
     * @param u de user die wordt gebruikt om te zoeken
     * @return een lijst met daarin alle metingen van de student
     */
    @Override
    public List<STEMMeasurement> findAllMeasurementsByStudent(User u) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT m FROM STEMMeasurement m WHERE m.student.user.login = '");
        query.append(u.getLogin());
        query.append("'");
        TypedQuery<STEMMeasurement> q =
                em.createQuery(query.toString(), STEMMeasurement.class);
        return q.getResultList();
    }

    /**
     * De projecten van een user worden gezocht
     *
     * @param user de user die wordt gebruikt om te zoeken
     * @return Het project waaraan de user heeft meegewerkt
     */
    @Override
    public StemProject findProjectByUser(User user) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT m FROM StemProject m WHERE m.student.user.login = '");
        query.append(user.getLogin());
        query.append("'");
        TypedQuery<StemProject> q =
                em.createQuery(query.toString(), StemProject.class);

        return q.getResultList().get(0);
    }

    /**
     * Een project vinden op basis van zijn ID
     * @param id De long ID
     * @return Het betreffende project
     */
    @Override
    public StemProject findProject(long id){
        return em.find(StemProject.class, id);
    }

    /**
     * Generische methode op project aan te passen in de database
     * @param t
     * @param <T>
     */
    @Override
    public <T> void updateDB(T t) {

        em.merge(t);
    }


    //Methode aanmaken student projecten

    //methode voor vinden van alle projecten van ne teacher


}

