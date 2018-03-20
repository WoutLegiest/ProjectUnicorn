package be.kuleuven.gent.project.ejb;

import javax.ejb.Local;
import java.util.LinkedList;
import java.util.List;

@Local
public interface ApplicationManagementEJBLocal {

    public LinkedList<LinkedList<Double>> verwerkData(LinkedList<LinkedList<Double>> input );
}
