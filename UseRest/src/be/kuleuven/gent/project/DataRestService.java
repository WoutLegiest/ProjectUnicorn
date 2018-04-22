package be.kuleuven.gent.project;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("data")
@Produces(MediaType.TEXT_PLAIN)
public class DataRestService {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getDataByMeasurement(@HeaderParam("idData") int idData){
        //TODO data en meting opsplitsen in de jpa's, data management ejb maken die data object kan terug geven op basis van idData.
        return null;
    }
}
