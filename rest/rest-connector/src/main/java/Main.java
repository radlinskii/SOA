import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.core.Response;

public class Main {
    public static void main(String[] args) {
            ResteasyClient client = new ResteasyClientBuilder().build();
            ResteasyWebTarget target = client.target("http://localhost:8080/rest-web/student");
            Response response = target.request().get();
            String value = response.readEntity(String.class);
            System.out.println(value);
            response.close();
    }

}
