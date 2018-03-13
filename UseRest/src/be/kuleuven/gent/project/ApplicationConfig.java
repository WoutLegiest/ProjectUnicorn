package be.kuleuven.gent.project;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;

import com.owlike.genson.ext.jaxrs.GensonJsonConverter;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("rest_unicorn")
public class ApplicationConfig extends Application {

	private final Set<Class<?>> classes;
	
	final ResourceConfig config = new ResourceConfig().register(GensonJsonConverter.class);

	public ApplicationConfig() {
		HashSet<Class<?>> c = new HashSet<>();
		c.add(BasicRestService.class);
		c.add(ParameterRestService.class);
		c.add(LoginAndRegister.class);
		c.add(ContentTypesRestService.class);
		c.add(ResponseRestService.class);

		classes = Collections.unmodifiableSet(c);
	}

	@Override
	public Set<Class<?>> getClasses() {
		return classes;
	}
}