package be.kuleuven.gent.project;

import be.kuleuven.gent.project.data.ProfessionalMeasurement;
import be.kuleuven.gent.project.data.ProfessionalProject;
import be.kuleuven.gent.project.ejb.ProfessionalMeasurementManagementEJBLocal;
import be.kuleuven.gent.project.ejb.SpotterProjectManagementEJBLocal;
import com.owlike.genson.Genson;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("SpotterProjectData")
@Produces(MediaType.TEXT_PLAIN)
public class SpotterProjectData {

    @EJB
    private SpotterProjectManagementEJBLocal spmejbl;
    private ProfessionalMeasurementManagementEJBLocal semejbl;

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
        List<ProfessionalProject> projects=spmejbl.findAllProjects();
        return Response.ok(projects, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/metingen")
    public Response getMetingenByProject(@HeaderParam("projectid") long projectID){
        ProfessionalProject professionalProject =spmejbl.findProject(projectID);
        List<ProfessionalMeasurement>spottermeringen=semejbl.findAllMeasurementsByProject(professionalProject);
        return Response.ok(spottermeringen, MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/newProject")
    public Response newProject( ){

        return null;
    }

}
