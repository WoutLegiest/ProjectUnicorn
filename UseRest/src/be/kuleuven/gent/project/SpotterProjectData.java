package be.kuleuven.gent.project;

import be.kuleuven.gent.project.data.ProfessionalMeasurement;
import be.kuleuven.gent.project.data.ProfessionalProject;
import be.kuleuven.gent.project.data.User;
import be.kuleuven.gent.project.ejb.ProfessionalMeasurementManagementEJBLocal;
import be.kuleuven.gent.project.ejb.ProfessionalProjectManagementEJBLocal;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;
import com.owlike.genson.Genson;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * REST service voor het ophalen van informatie over projecten
 */
@Path("ProfProjectData")
@Produces(MediaType.TEXT_PLAIN)
public class SpotterProjectData {

    @EJB
    private ProfessionalProjectManagementEJBLocal spmejbl;
    @EJB
    private ProfessionalMeasurementManagementEJBLocal semejbl;
    @EJB
    private UserManagementEJBLocal umejbl;

    /**
     *Er worden projecten gezocht op basis van de breedte- en lengtegraad en deze worden teruggeven in een JSON vorm
     * @param latitude De breedtegraad
     * @param longitude De lengtegraad
     * @return String met daarin alle locaties, in JSON vorm
     */
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

    /**
     * Alle projecten worden opgevraagd
     * @return een Response, in JSON vorm, met daarin alle projecten
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/all")
    public Response getAllProjects(){
        List<ProfessionalProject> projects=spmejbl.findAllProjects();

        return Response.ok(projects, MediaType.APPLICATION_JSON).build();
    }

    /**
     * alle project namen worden opgevraagd
     * @return een Response, in JSON vorm, met daarin alle projecten titels
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/titles")
    public Response getAllProjectsNames(){
        List<ProfessionalProject> projects=spmejbl.findAllProjects();
        List<String> projectTitles = new ArrayList<>();
        for(ProfessionalProject pp : projects){
            projectTitles.add(pp.getName());
        }

        return Response.ok(projectTitles, MediaType.APPLICATION_JSON).build();
    }


}
