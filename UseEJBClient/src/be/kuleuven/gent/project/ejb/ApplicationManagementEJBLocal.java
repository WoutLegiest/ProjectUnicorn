package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.ProfessionalMeasurement;

import javax.ejb.Local;
import java.io.IOException;
import java.util.ArrayList;

@Local
public interface ApplicationManagementEJBLocal {

    void addMeaserment(ProfessionalMeasurement pf);

    String processData() throws IOException;

    String maakScript() throws IOException;

    ProfessionalMeasurement makePM();
}
