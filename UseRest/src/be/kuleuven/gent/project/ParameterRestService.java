package be.kuleuven.gent.project;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("parameter_service")
public class ParameterRestService {

	@GET
	@Path("path/{text}")
	public String pathParamService(@PathParam("text") String userText)
	{
		return "Path param: " + userText;
	}
	
	@GET
	@Path("query")
	public String queryParamService(@QueryParam("zip") Long zip, @QueryParam("city") String city)
	{
		return "Zip: " + zip + " and city: " + city;
	}
	
	@GET
	@Path("header")
	public String headerParamService(@HeaderParam("User-Agent") String userAgent)
	{
		return "user agent: " + userAgent;
	}
	
	@POST
	public String addUser(@FormParam("name") String name, @FormParam("age") int age, 
			@DefaultValue("default value") @FormParam("optional") String optional) 
	{
		return "name: " + name + " and age: " + age + " and optional: " + optional;
	}
	
	
}
