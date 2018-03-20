package be.kuleuven.gent.project.ejb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.LinkedList;

public class SpotterExperimentManagementEJB implements SpotterExperimentManagementEJBLocal {

    @PersistenceContext(unitName="unicorn")
    private EntityManager em;

    public SpotterExperimentManagementEJB() {
    }

    @Override
    public boolean experimentVerwerken(LinkedList<LinkedList<Double>> input) {

        return false;
    }
}
