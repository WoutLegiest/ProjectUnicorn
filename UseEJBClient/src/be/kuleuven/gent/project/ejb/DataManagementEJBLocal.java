package be.kuleuven.gent.project.ejb;

import be.kuleuven.gent.project.data.Data;

import javax.ejb.Local;

@Local
public interface DataManagementEJBLocal {

    Data findData(long id);
}
