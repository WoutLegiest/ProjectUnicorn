package be.kuleuven.gent.project;

import be.kuleuven.gent.project.data.SpotterProject;
import be.kuleuven.gent.project.data.Spottermeting;
import be.kuleuven.gent.project.ejb.SpotterExperimentManagementEJBLocal;
import be.kuleuven.gent.project.ejb.SpotterProjectManagementEJBLocal;
import com.owlike.genson.Genson;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("SpotterProjectData")
@Produces(MediaType.TEXT_PLAIN)
public class SpotterProjectData {

    @EJB
    private SpotterProjectManagementEJBLocal spmejbl;
    private SpotterExperimentManagementEJBLocal semejbl;

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
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/all")
    public Response getAllProjects(){
        List<SpotterProject> projects=spmejbl.findAllProjects();
        return Response.ok(projects, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/metingen")
    public Response getMetingenByProject(@HeaderParam("projectid") long projectID){
        SpotterProject spotterProject=spmejbl.findProject(projectID);
        List<Spottermeting>spottermeringen=semejbl.findAllMetingen(spotterProject);
        return Response.ok(spottermeringen, MediaType.APPLICATION_JSON).build();
    }
}
