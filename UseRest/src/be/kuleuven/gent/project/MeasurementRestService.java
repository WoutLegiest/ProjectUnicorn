package be.kuleuven.gent.project;

import be.kuleuven.gent.project.data.ProfessionalMeasurement;
import be.kuleuven.gent.project.ejb.ApplicationManagementEJBLocal;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("measurement_service")
public class MeasurementRestService {

    //Put voor Used to create a resource, or overwrite it. While you specify the resources new URL.
    //Post voor Used to modify and update a resource

    @EJB
    private ApplicationManagementEJBLocal app;



    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    public Response addMeasService(byte[] x, byte[] y, byte[] z) {

        ProfessionalMeasurement pf =app.makePM();

        pf.setxData(x);
        pf.setyData(y);
        pf.setzData(z);

        app.addMeaserment(pf);

        return null;
    }
}

