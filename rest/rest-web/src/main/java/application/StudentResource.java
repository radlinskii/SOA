package application;

import application.StudentContainer;
import filter.JWTNeeded;
import model.Student;
import org.apache.commons.codec.binary.Base64;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.stream.Collectors;

@Path("student")
public class StudentResource {

    @Inject
    StudentContainer container;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@QueryParam("faculty") String facultyFilter, @QueryParam("course") String courseFilter) {

        List<Student> students = container.all();

        if (facultyFilter != null) {
            students = students.stream().filter(s -> s.getFaculty().equals(facultyFilter)).collect(Collectors.toList());
        }

        if (courseFilter != null) {
            students = students.stream().filter(s -> s.getCourses().contains(courseFilter)).collect(Collectors.toList());
        }

        return Response.ok(students).build();
    }

    @GET
    @Path("{studentCardId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("studentCardId") int studentCardId) {
        Student student = container.get(studentCardId);

        if (student == null) {
            throw new NotFoundException();
        }

        return Response.ok(student).build();
    }


    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(
            @FormParam("name") String name,
            @FormParam("studentCardId") Integer studentCardId,
            @FormParam("faculty") String faculty,
            @FormParam("semester") Integer semester,
            @FormParam("courses") List<String> courses,
            @FormParam("avatar") String avatar,
            @Context UriInfo uriInfo
    ) {

        if (
                name == null ||
                studentCardId == null ||
                faculty == null ||
                semester == null ||
                courses == null ||
                avatar == null
        ) {
            throw new BadRequestException();
        }


        Student student = new Student(name, studentCardId, faculty, semester, courses, Base64.encodeBase64URLSafeString(avatar.getBytes()));
        container.addStudent(student);

        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(studentCardId));
        return Response.created(builder.build()).entity(student).build();
    }


    @PUT
    @Path("{oldStudentCardId}")
    @JWTNeeded
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Student editStudent(
            @PathParam("oldStudentCardId") int oldStudentCardId,
            @FormParam("name") String newName,
            @FormParam("studentCardId") Integer newStudentCardId,
            @FormParam("faculty") String newFaculty,
            @FormParam("semester") Integer newSemester,
            @FormParam("courses") List<String> newCourses,
            @FormParam("avatar") String newAvatar
    ) {
        Student student = container.get(oldStudentCardId);

        System.out.println(student);

        if (newStudentCardId != null) {
            student.setStudentCardId(newStudentCardId);
            container.delete(oldStudentCardId);
        }

        if (newName != null) {
            student.setName(newName);
        }

        if (newFaculty != null) {
            student.setFaculty(newFaculty);
        }

        if (newSemester != null) {
            student.setSemester(newSemester);
        }

        if (newCourses != null) {
            student.setCourses(newCourses);
        }

        if (newAvatar != null) {
            String encodedNewAvatar = Base64.encodeBase64URLSafeString(newAvatar.getBytes());
            student.setAvatar(encodedNewAvatar);
        }
        container.addStudent(student);

        return student;
    }
}
