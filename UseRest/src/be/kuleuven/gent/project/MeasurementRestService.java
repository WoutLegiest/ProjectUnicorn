package be.kuleuven.gent.project;

import be.kuleuven.gent.project.Support.DataAdapter;
import be.kuleuven.gent.project.data.*;
import be.kuleuven.gent.project.ejb.*;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * REST Service voor het verwerken van metingen
 */
@Path("measurement_service")
public class MeasurementRestService {

    //Put voor Used to create a resource, or overwrite it. While you specify the resources new URL.
    //Post voor Used to modify and update a resource

    @EJB
    private MeasurementManagementEJBLocal app;

    @EJB
    private UserManagementEJBLocal userManagementEJBLocal;

    @EJB
    private ProfessionalProjectManagementEJBLocal professionalProjectManagementEJBLocal;

    @EJB
    private DataManagementEJBLocal dataManagementEJBLocal;

    @EJB
    private ProfessionalMeasurementManagementEJBLocal professionalMeasurementManagementEJBLocal;

    @EJB
    private StemMeasurementManagementEJBLocal stemMeasurementManagementEJBLocal;

    /**
     * Methode die de data ontvangt van de applicatie, deze klaarmaakt voor het verwerken via Octave
     * @param jsonInput De input data uit de applicatie
     * @return Response, in JSON vorm, waarin een dataAdapter zit met daarin alle data. Zowel de gemeten data als de verwerkte data.
     */
    @POST
    @Path("/data_process")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response analyzeData(String jsonInput)  {

        ArrayList<ArrayList<Float>> results = new ArrayList<>();

        ArrayList<Float> xDataInput =new ArrayList<>();
        ArrayList<Float> yDataInput =new ArrayList<>();
        ArrayList<Float> zDataInput =new ArrayList<>();
        ArrayList<ArrayList<Float>> dataInput = new ArrayList<>();

        try {
            JSONObject inputObject = new JSONObject(jsonInput);
            JSONArray xArray = inputObject.getJSONArray("xData");
            JSONArray yArray = inputObject.getJSONArray("yData");
            JSONArray zArray = inputObject.getJSONArray("zData");

            for(int i=0;i<xArray.length();i++){
                xDataInput.add(Float.parseFloat(xArray.getString(i)));
                yDataInput.add(Float.parseFloat(yArray.getString(i)));
                zDataInput.add(Float.parseFloat(zArray.getString(i)));
            }

            dataInput.add(xDataInput);
            dataInput.add(yDataInput);
            dataInput.add(zDataInput);

            for (ArrayList<Float> dataList : dataInput){

                ArrayList<ArrayList<Float>> ejbResultList=app.processData(dataList);
                for (ArrayList<Float> ejbresult : ejbResultList){
                    results.add(ejbresult);
                }
            }

            Data data=dataManagementEJBLocal.makeDataObject(dataInput,results);

            DataAdapter dataAdapter = new DataAdapter(data.getId(),data );
            dataAdapter.setId(data.getId());
            return Response.ok(dataAdapter, MediaType.APPLICATION_JSON).build();
        } catch (JSONException e) {
            return Response.ok(e, MediaType.APPLICATION_JSON).build();
        } catch (IOException e) {
            return Response.ok(e, MediaType.APPLICATION_JSON).build();
        }
    }

    /**
     * Er wordt een professionele meting aangemaakt en op de server gezet
     * @param jsonInput De input data uit de applicatie
     * @return Response, in JSON vorm, van het aangemaakte professionele meting
     */
    @POST
    @Path("/measurement_registration")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response registerMeasurement(String jsonInput)  {
        try {
            JSONObject jsonObject = new JSONObject(jsonInput);
            JSONObject projectJSON = jsonObject.getJSONObject("project");
            long projectID = projectJSON.getLong("id");
            long dataID = jsonObject.getLong("idData");
            String login = jsonObject.getString("proUser_User_LoginName");
            String description = jsonObject.getString("description");
            Long dateLong = jsonObject.getLong("datum");

            Date dateSql = new Date(dateLong);
            ProUser proUser = userManagementEJBLocal.findProUser(login);
            ProfessionalProject professionalProject= professionalProjectManagementEJBLocal.findProject(projectID);
            Data data = dataManagementEJBLocal.findData(dataID);
            ProfessionalMeasurement professionalMeasurement = professionalMeasurementManagementEJBLocal.makeMeasurement(proUser,professionalProject,data,description,dateSql);
            return Response.ok(professionalMeasurement,MediaType.APPLICATION_JSON).build();
        } catch (JSONException e) {
            e.printStackTrace();
            return Response.ok(e,MediaType.APPLICATION_JSON).build();
        }
    }

    @POST
    @Path("/STEMmeasurement_registration")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response registerSTEMMeasurement(String jsonInput)  {
        try {
            JSONObject jsonObject = new JSONObject(jsonInput);
            long dataID = jsonObject.getLong("idData");
            String login = jsonObject.getString("login");
            String description = jsonObject.getString("description");
            Long dateLong = jsonObject.getLong("datum");

            Date dateSql = new Date(dateLong);
            Student student = userManagementEJBLocal.findStudent(login);
            Data data = dataManagementEJBLocal.findData(dataID);
            STEMMeasurement stemMeasurement = stemMeasurementManagementEJBLocal.makeMeasurement(student,dataID,description,dateSql);
            return Response.ok(stemMeasurement,MediaType.APPLICATION_JSON).build();
        } catch (JSONException e) {
            e.printStackTrace();
            return Response.ok(e,MediaType.APPLICATION_JSON).build();
        }
    }


}

