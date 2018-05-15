package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.STEMMeasurement;
import be.kuleuven.gent.project.data.Student;

import javax.ejb.Local;
import java.sql.Date;

@Local
public interface StemMeasurementManagementEJBLocal {

    STEMMeasurement findMeasurementById(Long id);

    STEMMeasurement makeMeasurement(Student student, long dataID, String description, Date dateSql);

}
