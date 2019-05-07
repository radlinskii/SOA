import model.Student;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.core.Response;

public class Main {
    public static void main(String[] args) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/rest-web/student/123456");
        Response response = target.request().get();
        Student student = response.readEntity(Student.class);
        System.out.println(student);
        response.close();

        ResteasyWebTarget target2 = client.target("http://localhost:8080/rest-web/student");
        Response response2 = target2.request().get();
        Student[] students = response2.readEntity(Student[].class);
        for (Student s : students) {
            System.out.println(s);
        }
        response2.close();
    }
}

// jwt admin admin