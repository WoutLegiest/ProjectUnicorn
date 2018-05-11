package be.kuleuven.gent.project;

import be.kuleuven.gent.project.Support.DataAdapter;
import be.kuleuven.gent.project.data.Data;
import be.kuleuven.gent.project.ejb.DataManagementEJBLocal;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("data")
@Produces(MediaType.TEXT_PLAIN)
public class DataRestService {

    @EJB
    private DataManagementEJBLocal dmejbl;


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getDataByMeasurement(@HeaderParam("idData") long idData){
        Data data = dmejbl.findData(idData);
        DataAdapter dataAdapter = new DataAdapter(idData, data);
        return Response.ok(dataAdapter, MediaType.APPLICATION_JSON).build();
    }
}
