package be.kuleuven.gent.project.ejb;

import javax.ejb.Local;
import java.io.IOException;

@Local
public interface ApplicationManagementEJBLocal {

    String processData() throws IOException;

    void maakScript();
}
