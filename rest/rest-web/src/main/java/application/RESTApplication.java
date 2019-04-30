package application;


import filter.JWTNeededFilter;
import resources.AuthResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class RESTApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes=new HashSet<>();
        classes.add(StudentResource.class);
        classes.add(AuthResource.class);
        classes.add(JWTNeededFilter.class);
        return classes;
    }
}