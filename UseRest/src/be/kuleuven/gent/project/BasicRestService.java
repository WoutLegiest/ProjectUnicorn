package be.kuleuven.gent.project;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("basic_service")
public class BasicRestService {
	
	@GET
	public Boolean getBasicService()
	{
		System.out.println("get basic service");
		return true;
	}
	
	@POST
	public Boolean postBasicService()
	{
		System.out.println("post basic service");
		return true;
	}
	
	@DELETE
	public Boolean deleteBasicService()
	{
		System.out.println("delete basic service");
		return true;
	}
}
