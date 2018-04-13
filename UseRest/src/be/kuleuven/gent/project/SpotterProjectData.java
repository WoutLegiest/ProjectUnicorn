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
import java.util.ArrayList;
import java.util.List;

@Path("ProfProjectData")
@Produces(MediaType.TEXT_PLAIN)
public class SpotterProjectData {

    @EJB
    private SpotterProjectManagementEJBLocal spmejbl;
    private ProfessionalMeasurementManagementEJBLocal semejbl;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getProjectLocations(@HeaderParam("latitude") String latitude, @HeaderParam("longitude") String longitude){

        List<String> locations= spmejbl.projectByLocations(Float.parseFloat(latitude), Float.parseFloat(longitude));
        StringBuilder json =new StringBuilder();
        Genson genson = new Genson();
        for (String s : locations){
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
    @Path("/titles")
    public Response getAllProjectsNames(){
        List<ProfessionalProject> projects=spmejbl.findAllProjects();
        List<String> protjectTitles = new ArrayList<>();
        for(ProfessionalProject pp : projects){
            protjectTitles.add(pp.getName());
        }

        return Response.ok(protjectTitles, MediaType.APPLICATION_JSON).build();
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/newProject")
    public Response newProject( ){

        return null;
    }

}
