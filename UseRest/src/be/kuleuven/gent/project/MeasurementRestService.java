package be.kuleuven.gent.project;

import be.kuleuven.gent.project.Support.DataAdapter;
import be.kuleuven.gent.project.data.Data;
import be.kuleuven.gent.project.data.ProfessionalMeasurement;
import be.kuleuven.gent.project.ejb.ApplicationManagementEJBLocal;
import org.glassfish.admin.amx.annotation.Param;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Path("measurement_service")
public class MeasurementRestService {

    //Put voor Used to create a resource, or overwrite it. While you specify the resources new URL.
    //Post voor Used to modify and update a resource

    @EJB
    private ApplicationManagementEJBLocal app;
    

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
            Data data=app.makeDataObject(dataInput,results);


            return Response.ok(data, MediaType.APPLICATION_JSON).build();
        } catch (JSONException e) {
            return Response.ok(e, MediaType.APPLICATION_JSON).build();
        } catch (IOException e) {
            return Response.ok(e, MediaType.APPLICATION_JSON).build();
        }
    }

    @POST
    @Path("/measurement_registration")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response registerMeasurement(String jsonInput)  {
        return null;
    }


}

