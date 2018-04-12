package be.kuleuven.gent.project;

import be.kuleuven.gent.project.Support.UserLight;
import be.kuleuven.gent.project.data.User;
import be.kuleuven.gent.project.data.UserToken;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;
import com.owlike.genson.Genson;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("login")
@Produces(MediaType.TEXT_PLAIN)
public class LoginAndRegister {

    @EJB
    private UserManagementEJBLocal umejbl;

    @Path("/Pro")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response userLogin(@HeaderParam("UserCredentials") String userCredentials) {

        ArrayList<String> info= contractInformation(userCredentials);

        String userName = info.get(0);
        String hPasswd = info.get(1);


        StringBuilder json =new StringBuilder();
        Genson genson = new Genson();

        User user = umejbl.findPerson(userName);


        if (user!=null&& (user.getGroup().equals("ProUser")||user.getGroup().equals("Admin"))){
            UserLight userke = new UserLight(user.getId(),user.getFirstName(), user.getLastName(), user.getLogin(),user.getEmail(), user.getGroup());
            if(hPasswd.equals(user.gethPassword())){
                UserToken userToken = umejbl.findToken(user);
                if(userToken==null){
                    userToken =umejbl.createToken(user);
                }
                return Response.ok(userke,MediaType.APPLICATION_JSON).build();
            }else {
                String mislukt = "Inloggen mislukt: wachtwoord fout";
                json.append(genson.serialize(mislukt));
                return Response.ok(json.toString(),MediaType.APPLICATION_JSON).build();
            }
        } else {
            String mislukt = "Inloggen mislukt: usernaam bestaat niet";
            json.append(genson.serialize(mislukt));
            return Response.ok(json.toString(),MediaType.APPLICATION_JSON).build();
        }


    }

    @Path("/token")
    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getToken(@HeaderParam("UserCredentials") String userCredentials){
        ArrayList<String> info= contractInformation(userCredentials);

        String userName = info.get(0);
        String hPasswd = info.get(1);


        StringBuilder json =new StringBuilder();
        Genson genson = new Genson();

        User user = umejbl.findPerson(userName);
        if(user!=null&&hPasswd.equals(user.gethPassword())){
            UserToken userToken =umejbl.findToken(user);
            return Response.ok(userToken,MediaType.APPLICATION_JSON).build();
        }else{
            return null;
        }


    }

    private ArrayList<String> contractInformation(String info){
        String userName=null;
        String hPasswd;
        int i=6;
        boolean doorgaan=true;
        while(doorgaan){
            char ch = info.charAt(i);
            if(ch==':'){
                userName=info.substring(0,i );
                doorgaan=false;
            }
            i++;
        }
        hPasswd=info.substring(i,info.length());

        ArrayList<String> output =  new ArrayList<>();
        output.add(userName);
        output.add(hPasswd);
        return output;
    }


    /*
    @GET
    public Boolean getBasicService()
    {
        System.out.println("get basic service");
        return true;
    }
    */


}