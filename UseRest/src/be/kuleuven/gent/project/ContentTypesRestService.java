package be.kuleuven.gent.project;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("content_types")
@Produces(MediaType.TEXT_PLAIN)
public class ContentTypesRestService {

	
	
	@GET
	public String getAsPlainText() {
	     return "getAsPlainText";
	}

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String getAsHtml(){
	     return "getAsHtml";
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public String getAsJsonAndXML() {
		return "getAsJsonAndXML";
	}

}
