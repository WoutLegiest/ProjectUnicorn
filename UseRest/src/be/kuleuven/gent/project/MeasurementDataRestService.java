package be.kuleuven.gent.project;

import be.kuleuven.gent.project.Support.UserLight;
import be.kuleuven.gent.project.data.ProfessionalMeasurement;
import be.kuleuven.gent.project.data.ProfessionalProject;
import be.kuleuven.gent.project.data.User;
import be.kuleuven.gent.project.ejb.ProfessionalMeasurementManagementEJBLocal;
import be.kuleuven.gent.project.ejb.SpotterProjectManagementEJBLocal;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("ProfMeausermentData")
@Produces(MediaType.TEXT_PLAIN)
public class MeasurementDataRestService {

    @Inject
    private SpotterProjectManagementEJBLocal spmejbl;
    @Inject
    private ProfessionalMeasurementManagementEJBLocal semejbl;
    @Inject
    private UserManagementEJBLocal umejbl;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/all")
    public Response getMetingenByProject(@HeaderParam("projectid") long projectID){
        ProfessionalProject professionalProject =spmejbl.findProject(projectID);
        List<ProfessionalMeasurement> spottermeringen=semejbl.findAllMeasurementsByProject(professionalProject);
        return Response.ok(spottermeringen, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/own")
    public Response getMetingenByUser(@HeaderParam("userCredentials") String userCredentials){
        ArrayList<String> credentials = contractInformation(userCredentials);
        String username=credentials.get(0);

        User user = umejbl.findPerson(username);

        List<ProfessionalMeasurement> spottermeringen=semejbl.findAllMeasurementsByUser(user);
        return Response.ok(spottermeringen, MediaType.APPLICATION_JSON).build();
    }

    private ArrayList<String> contractInformation(String info){
        String userName=null;
        String tokenObj;
        int i=0;
        boolean doorgaan=true;
        while(doorgaan){
            char ch = info.charAt(i);
            if(ch==':'){
                userName=info.substring(0,i );
                doorgaan=false;
            }
            i++;
        }
        tokenObj=info.substring(i,info.length());
        String date= tokenObj.substring(0,10);
        String token = tokenObj.substring(11);

        ArrayList<String> output =  new ArrayList<>();
        output.add(userName);
        output.add(date);
        output.add(token);
        return output;
    }


}
