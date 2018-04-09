package be.kuleuven.gent.project.ejb;

import com.mathworks.engine.EngineException;

import javax.ejb.Local;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Local
public interface ApplicationManagementEJBLocal {

    public LinkedList<LinkedList<Double>> verwerkData(LinkedList<LinkedList<Double>> input ) throws InterruptedException, ExecutionException;
}
