package be.kuleuven.gent.project;

import be.kuleuven.gent.project.Support.DataAdapter;
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
    @Path("/data")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response analyzeData(String jsonInput)  {
        ArrayList<ArrayList> results = new ArrayList<>();

        ArrayList<Float> xDataInput =new ArrayList<>();
        ArrayList<Float> yDataInput =new ArrayList<>();
        ArrayList<Float> zDataInput =new ArrayList<>();
        ArrayList<ArrayList>dataInput = new ArrayList<>();
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
            dataInput.add(xDataInput);
            dataInput.add(xDataInput);
            for (ArrayList<Float> dataList : dataInput){

                ArrayList<ArrayList<Float>> ejbResultList=app.processData(dataList);
                for (ArrayList<Float> ejbresult : ejbResultList){
                    results.add(ejbresult);
                }
            }

            DataAdapter dataAdapter = new DataAdapter(results.get(0),results.get(1),results.get(2),results.get(3),results.get(4),results.get(5),results.get(6),results.get(7),results.get(8));
            return Response.ok(dataAdapter, MediaType.APPLICATION_JSON).build();
        } catch (JSONException e) {
            return Response.ok(e, MediaType.APPLICATION_JSON).build();
        } catch (IOException e) {
            return Response.ok(e, MediaType.APPLICATION_JSON).build();
        }
    }
}

