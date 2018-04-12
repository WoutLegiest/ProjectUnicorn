package be.kuleuven.gent.project;

import be.kuleuven.gent.project.Support.DataAdapter;
import be.kuleuven.gent.project.data.ProfessionalMeasurement;
import be.kuleuven.gent.project.ejb.ApplicationManagementEJBLocal;
import org.glassfish.admin.amx.annotation.Param;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

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
        try {
            JSONObject jsonObject = new JSONObject(jsonInput);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        //DataAdapter dataAdapter = (DataAdapter) jsonObject.get("data");
        //ArrayList<ArrayList> dataLists = dataAdapter.getData();

            /*for (ArrayList<Float> dataList : dataLists){
                //TODO laat ejb de analyse uitvoeren
                ArrayList<ArrayList> ejbResultList=ejbController.analyseer(dataList);
                for (ArrayList<Float> ejbresult : ejbResultList){
                    results.add(ejbresult);
                }
            }*/

        DataAdapter dataOutput = new DataAdapter(results);
        return Response.ok(jsonInput, MediaType.APPLICATION_JSON).build();
    }
}

