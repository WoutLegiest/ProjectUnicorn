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

    @PersistenceContext(unitName="unicorn")
    private EntityManager em;

    @Inject
    private UserManagementEJBLocal userman;

    /**
     * Default constructor
     */
    public StemProjectManagementEJB() {
    }

    /**
     * Er worden een aantal gebruiker aangemaakt indivduele projecten. Deze worden op de database gezet
     * @param sp
     * @param aantal Het aantal groepjes
     */
    @Override
    public void createProject(StemProject sp,int aantal) {

        String school = sp.getTeacher().getSchool();
        String klas = sp.getClassName();

        for (int i = 0; i < aantal ; i++) {

            StringBuilder sb = new StringBuilder();

            sb.append(school);
            sb.append(".student.");
            sb.append(klas);
            sb.append(".");
            sb.append(String.valueOf(i+1));


            User user = new User(sb.toString(),sb.toString(),"student");

            userman.createUser(user);
            userman.createStudent(user, school,klas,i);

            //Aanpassen JPA



        }


    }


    /**
     * Alle projecten worden gezocht van een bepaalde leerkracht
     * @param t Het leerkracht object
     * @return De lijst met al de StemProjecten die hij/zij opvolgd
     */
    @Override
    public List<StemProject> findAllProjectsByTeacher(Teacher t) {
        StringBuilder query = new StringBuilder();
        query.append("SELECT m FROM StemProject m WHERE m.teacher.user.login = '");
        query.append(t.getUser().getLogin());
        query.append("'");
        TypedQuery<StemProject> q =
                em.createQuery(query.toString() , StemProject.class);
        return q.getResultList();
    }

    /**
     * Er worden metingen gezocht op basis van de student
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
                em.createQuery(query.toString() , STEMMeasurement.class);
        return q.getResultList();
    }

    /**
     * De projecten van een user worden gezocht
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
                em.createQuery(query.toString() , StemProject.class);

        return q.getResultList().get(0);
    }


    //Methode aanmaken student projecten

    //methode voor vinden van alle projecten van ne teacher



}

