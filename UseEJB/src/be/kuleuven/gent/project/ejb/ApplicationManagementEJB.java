package be.kuleuven.gent.project.ejb;

import java.util.LinkedList;
import java.util.concurrent.ExecutionException;

import com.mathworks.engine.EngineException;
import com.mathworks.engine.MatlabEngine;

public class ApplicationManagementEJB implements ApplicationManagementEJBLocal {

    @Override
    public LinkedList<LinkedList<Double>> verwerkData(LinkedList<LinkedList<Double>> input ) throws InterruptedException, ExecutionException {
        //De lijsten moeten hier omgevormd worden naar het formaat dat het matlab scriptje verwacht

        MatlabEngine eng = MatlabEngine.startMatlab();
        double[] a = {2.0 ,4.0, 6.0};
        double[] roots = eng.feval("sqrt", a);
        for (double e: roots) {
            System.out.println(e);
        }
        eng.close();


        return null;
    }
}
