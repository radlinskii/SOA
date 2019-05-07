import model.Student;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("login with bad credentials");
        login("bad", "credentials");

        System.out.println("login with valid credentials");
        String authorizationHeader = login("admin", "admin");

        System.out.println("Authorization header");
        System.out.println(authorizationHeader);
        System.out.println();

        System.out.println("get list of students - should be empty");
        getList(null, null);

        System.out.println("add student 1");
        createStudent(authorizationHeader, "Jan Kowalski", 123456, 6, "EAIIB", Arrays.asList("PD", "HD"), "1234656543245");
        System.out.println("add student 2");
        createStudent(authorizationHeader, "Andrzej Nowak", 654321, 8, "WIET", Arrays.asList("SOA", "JIMP"), "4565434567876543");

        System.out.println("get list of students");
        getList(null, null);

        System.out.println("get list of students from EAIIB faculty");
        getList("EAIIB", null);

        System.out.println("get list of students that attend SOA course");
        getList(null, "SOA");

        System.out.println("get student 1 by id");
        getStudentById(123456);
        System.out.println("get student with not existing id");
        getStudentById(9999999);

        System.out.println("delete student 1");
        deleteById(authorizationHeader, 123456);
        System.out.println("delete student with invalid authorization token");
        deleteById("invalid token", 654321);
        System.out.println("delete student 2");
        deleteById(authorizationHeader, 654321);
        System.out.println("delete already not existing student");
        deleteById(authorizationHeader, 654321);

        System.out.println("get list of students - should be empty again");
        getList(null, null);


    }


    private static String login(String login, String password) {
        Form form = new Form();
        form.param("login", login).param("password", password);

        Entity<Form> entity = Entity.form(form);

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/rest-web/auth/login");
        Response response = target.request(MediaType.APPLICATION_JSON).post(entity);
        String authorizationHeader = response.getHeaderString("Authorization");
        System.out.println("login http://localhost:8080/rest-web/auth/login  POST " + response.getStatus());
        response.close();
        System.out.println();

        return authorizationHeader;
    }

    private static void createStudent(String authorizationHeader, String name, Integer studentCardId, Integer semester, String faculty, List<String> courses, String avatar) {
        Form form = new Form();
        form.param("name", name)
                .param("studentCardId", studentCardId.toString())
                .param("semester", semester.toString())
                .param("faculty", faculty)
                .param("avatar", avatar);

        for (String course : courses) {
            form.param("courses", course);
        }

        Entity<Form> entity = Entity.form(form);

        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/rest-web/student");
        Response response = target.request(MediaType.APPLICATION_JSON)
                .header("Authorization", authorizationHeader)
                .post(entity);
        Student student = response.readEntity(Student.class);
        System.out.println("createStudent http://localhost:8080/rest-web/student  POST " + response.getStatus());
        System.out.println(student);
        System.out.println();

        response.close();
    }


    private static void getList(String facultyFilter, String courseFilter) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        String uri = "http://localhost:8080/rest-web/student";
        if (facultyFilter != null) {
            uri += "?faculty=" + facultyFilter;
        }
        if (courseFilter != null) {
            uri += facultyFilter != null ? "&" : "?";
            uri += "course=" + courseFilter;
        }
        ResteasyWebTarget target = client.target(uri);
        Response response = target.request().get();
        System.out.println("getList http://localhost:8080/rest-web/student  GET " + response.getStatus());
        Student[] students = response.readEntity(Student[].class);
        for (Student s : students) {
            System.out.println(s);
        }
        System.out.println();

        response.close();
    }


    private static void getStudentById(Integer studentCardId) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/rest-web/student/" + studentCardId.toString());
        Response response = target.request().get();
        int responseStatus = response.getStatus();
        System.out.println("getStudentById http://localhost:8080/rest-web/student/" + studentCardId.toString() + "  GET " + responseStatus);
        if(responseStatus == Response.Status.OK.getStatusCode()) {
            Student student = response.readEntity(Student.class);
            System.out.println(student);
        }
        System.out.println();

        response.close();
    }

    private static void deleteById(String authorizationHeader, Integer studentCardId) {
        ResteasyClient client = new ResteasyClientBuilder().build();
        ResteasyWebTarget target = client.target("http://localhost:8080/rest-web/student/" + studentCardId.toString());
        Response response = target.request()
                .header("Authorization", authorizationHeader)
                .delete();
        int responseStatus = response.getStatus();
        System.out.println("deleteById http://localhost:8080/rest-web/student/" + studentCardId.toString() + "  DELETE " + responseStatus);
        if(responseStatus == Response.Status.OK.getStatusCode()) {
            Student student = response.readEntity(Student.class);
            System.out.println(student);
        }
        System.out.println();

        response.close();
    }
}


// TODO PUT
// TODO AVATAR