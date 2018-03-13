package be.kuleuven.gent.project;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("LoginOrRegister")
@Produces(MediaType.TEXT_PLAIN)
public class LoginAndRegister {


    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getToken() {

        return "getAsPlainText";
    }


}