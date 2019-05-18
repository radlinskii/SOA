package application;


import authorization.AuthResource;
import authorization.JWTNeededFilter;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class RESTApplication extends Application {
    public RESTApplication() {
        BeanConfig beanConfig = new BeanConfig();

        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[] {"http"});
        beanConfig.setTitle("student API");
        beanConfig.setHost("localhost:8080");
        beanConfig.setBasePath("/rest-web/");
        beanConfig.setResourcePackage("application");
        beanConfig.setScan(true);
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes=new HashSet<>();
        classes.add(StudentResource.class);
        classes.add(AuthResource.class);
        classes.add(JWTNeededFilter.class);
        classes.add(ApiListingResource.class);
        classes.add(SwaggerSerializers.class);
        return classes;
    }
}