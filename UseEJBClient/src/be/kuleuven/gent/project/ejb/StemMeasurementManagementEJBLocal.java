package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.STEMMeasurement;

import javax.ejb.Local;

@Local
public interface StemMeasurementManagementEJBLocal {

    STEMMeasurement findMeasurementById(Long id);

}
