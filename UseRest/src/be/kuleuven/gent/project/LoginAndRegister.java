package be.kuleuven.gent.project;

import be.kuleuven.gent.project.data.User;
import be.kuleuven.gent.project.ejb.UserManagementEJBLocal;
import com.owlike.genson.Genson;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("LoginOrRegister")
@Produces(MediaType.TEXT_PLAIN)
public class LoginAndRegister {

    @EJB
    private UserManagementEJBLocal umejbl;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String getToken(@HeaderParam("Authorization") String userCredentials) {

        String userName=null;
        String hPasswd;
        int i=6;
        boolean doorgaan=true;
        while(doorgaan){
            char ch = userCredentials.charAt(i);
            if(ch==':'){
                userName=userCredentials.substring(6,i );
                doorgaan=false;
            }
            i++;
        }
        hPasswd=userCredentials.substring(i,userCredentials.length());

        StringBuilder json =new StringBuilder();
        Genson genson = new Genson();

        User user = umejbl.findPerson(userName);
        if (user!=null){
            UserLight userke = new UserLight(user.getId(),user.getName(), user.getLogin(),user.getEmail(), user.getGroup());
            if(hPasswd.equals(user.gethPassword())){
                userke.setToken(umejbl.createToken(user));
                json.append(genson.serialize(userke));
            }else {
                String mislukt = "Inloggen mislukt: wachtwoord fout";
                json.append(genson.serialize(mislukt));
            }
        } else {
            String mislukt = "Inloggen mislukt: usernaam bestaat niet";
            json.append(genson.serialize(mislukt));
        }

        return json.toString();
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