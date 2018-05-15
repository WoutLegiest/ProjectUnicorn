package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.*;

import javax.ejb.Local;
import java.util.List;
@Local
public interface STEMMeasurementManagementEJBLocal {

    STEMMeasurement makeMeasurement(Student student, long idData, String description, java.sql.Date date);

}
