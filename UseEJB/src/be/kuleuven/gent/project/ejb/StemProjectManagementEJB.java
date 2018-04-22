package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.StemProject;
import be.kuleuven.gent.project.data.Teacher;
import be.kuleuven.gent.project.data.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class StemProjectManagementEJB implements StemProjectManagementEJBLocal {

    //Methode om de verschillende studeten groepen te creeen, automatisch en deze dan te weergeven op de teacher pagina

    @PersistenceContext(unitName="unicorn")
    private EntityManager em;

    @Inject
    private UserManagementEJBLocal userman;

    public StemProjectManagementEJB() {
    }

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

    //Methode aanmaken student projecten

    //methode voor vinden van alle projecten van ne teacher



}

