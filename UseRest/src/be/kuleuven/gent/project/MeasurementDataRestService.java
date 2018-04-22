package be.kuleuven.gent.project;

import be.kuleuven.gent.project.Support.DataAdapter;
import be.kuleuven.gent.project.data.Data;
import be.kuleuven.gent.project.data.ProfessionalMeasurement;
import be.kuleuven.gent.project.data.ProfessionalProject;
import be.kuleuven.gent.project.data.User;
import be.kuleuven.gent.project.ejb.ProfessionalMeasurementManagementEJBLocal;
import be.kuleuven.gent.project.ejb.ProfessionalProjectManagementEJBLocal;
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

/**
 * Een Rest service voor het verwerken van alle Professionele Metingen
 */
@Path("ProfMeasurementData")
@Produces(MediaType.TEXT_PLAIN)
public class MeasurementDataRestService {

    @EJB
    private ProfessionalProjectManagementEJBLocal spmejbl;
    @EJB
    private ProfessionalMeasurementManagementEJBLocal semejbl;
    @EJB
    private UserManagementEJBLocal umejbl;

    /**
     * Alle metingen van een specifiek project worden opgevraagd. Het gebruikt ProfessionalProjectManagementEJB en
     * ProfessionalMeasurementManagementEJB
     * @param projectID een project ID voor het zoeken
     * @return Response, in JSON vorm, van alle metingen
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/all")
    public Response getMeasurementsByProject(@HeaderParam("projectId") long projectID) {
        ProfessionalProject professionalProject = spmejbl.findProject(projectID);
        List<ProfessionalMeasurement> spotterMeasurements = semejbl.findAllMeasurementsByProject(professionalProject);
        return Response.ok(spotterMeasurements, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Voor het ophalen van alle metingen door een bepaalde gebruiker.
     * Gebruikt UserManagementEJB en ProfessionalMeasurementManagementEJB
     * @param userCredentials De gevens van de gebruiker
     * @return Response, in JSON vorm, van alle metingen
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/own")
    public Response getMeasurementsByUser(@HeaderParam("UserCredentials") String userCredentials) {
        ArrayList<String> credentials = contractInformation(userCredentials);
        String username = credentials.get(0);

        User user = umejbl.findPerson(username);

        List<ProfessionalMeasurement> spotterMeasurements = semejbl.findAllMeasurementsByUser(user);
        return Response.ok(spotterMeasurements, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Vraag de Data op van een bepaalde meting. Gebruikt ProfessionalMeasurementManagementEJB
     * @param measurementId De ID van de meting
     * @return Response, in JSON vorm, van de Data. Er wordt gebruikt gemaakt van de DataAdapter
     */
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    @Path("/data")
    public Response getDataByMeasurement(@HeaderParam("measurementId") long measurementId) {
        ProfessionalMeasurement spotterMeasurement = semejbl.findMeasurementById(measurementId);
        Data data = spotterMeasurement.getData(); //Byte data
        DataAdapter dataAdapter = new DataAdapter(data.getId(), data);

        return Response.ok(dataAdapter, MediaType.APPLICATION_JSON).build();
    }

    /**
     * Haalt info uit de doorgegeven string
     * @param info string waaruit de informatie wordt gehaald
     * @return De eigenschaooen van de user worden in een lijst doorgegeven
     */
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
