package be.kuleuven.gent.project;

import be.kuleuven.gent.project.data.ProfessionalMeasurement;
import be.kuleuven.gent.project.data.STEMMeasurement;
import be.kuleuven.gent.project.data.StemProject;
import be.kuleuven.gent.project.data.User;
import be.kuleuven.gent.project.ejb.ProfessionalMeasurementManagementEJBLocal;
import be.kuleuven.gent.project.ejb.ProfessionalProjectManagementEJBLocal;
import be.kuleuven.gent.project.ejb.StemProjectManagementEJBLocal;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("stem_measurement_service")
public class StemMeasurementRestService {

    @EJB
    private StemProjectManagementEJBLocal spmejb;
    @EJB
    private ProfessionalMeasurementManagementEJBLocal semejbl;
    @EJB
    private UserManagementEJBLocal umejbl;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/own")
    public Response getMeasurementsByUser(@HeaderParam("UserCredentials") String userCredentials) {
        ArrayList<String> credentials = contractInformation(userCredentials);
        String username = credentials.get(0);

        User user = umejbl.findPerson(username);

        List<STEMMeasurement> stemMeasurements = spmejb.findAllMeasurementsByStudent(user);
        return Response.ok(stemMeasurements, MediaType.APPLICATION_JSON).build();
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/project")
    public Response getProjectByUser(@HeaderParam("UserCredentials") String userCredentials) {
        ArrayList<String> credentials = contractInformation(userCredentials);
        String username = credentials.get(0);

        User user = umejbl.findPerson(username);

        StemProject stemProject = spmejb.findProjectByUser(user);
        return Response.ok(stemProject, MediaType.APPLICATION_JSON).build();
    }



    private ArrayList<String> contractInformation(String info) {
        String userName = null;
        String tokenObj;
        int i = 0;
        boolean doorgaan = true;
        while (doorgaan) {
            char ch = info.charAt(i);
            if (ch == ':') {
                userName = info.substring(0, i);
                doorgaan = false;
            }
            i++;
        }
        tokenObj = info.substring(i, info.length());
        String date = tokenObj.substring(0, 10);
        String token = tokenObj.substring(11);

        ArrayList<String> output = new ArrayList<>();
        output.add(userName);
        output.add(date);
        output.add(token);
        return output;
    }
}
