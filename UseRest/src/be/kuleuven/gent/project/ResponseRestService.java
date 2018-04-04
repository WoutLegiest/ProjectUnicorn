package be.kuleuven.gent.project;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("books")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ResponseRestService {

	@Context
	private UriInfo uriInfo;


	@POST
	public Response createBook() {

		return null;
	}

	@GET
	public Response getAllBooks() {

		return null;
	}

}
