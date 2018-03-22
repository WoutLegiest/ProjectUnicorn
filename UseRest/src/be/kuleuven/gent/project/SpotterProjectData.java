package be.kuleuven.gent.project;

import be.kuleuven.gent.project.data.SpotterProject;
import be.kuleuven.gent.project.ejb.SpotterProjectManagementEJBLocal;
import com.owlike.genson.Genson;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;

@Path("SpotterProjectData")
@Produces(MediaType.TEXT_PLAIN)
public class SpotterProjectData {

    @EJB
    private SpotterProjectManagementEJBLocal spmejbl;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getProjectLocations(@HeaderParam("Latitude") String latitude, @HeaderParam("Longitude") String longitude){

        List<String> locaties= spmejbl.projectByLocations(Float.parseFloat(latitude), Float.parseFloat(longitude));
        StringBuilder json =new StringBuilder();
        Genson genson = new Genson();
        for (String s : locaties){
            json.append(genson.serialize(s));
        }
        return json.toString();

        //return "getter gelukt";
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/all")
    public Response getAllProjects(){
        List<SpotterProject> projects=spmejbl.findAllProjects();
        return Response.ok(projects, MediaType.APPLICATION_JSON).build();
    }

    /*
    @GET
    public String getBasicService() {

        return "UseWebWeb/rest_unicorn/SpotterProjectData/";
    }
    */
}
