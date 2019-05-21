package application;

import authorization.JWTNeeded;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import model.Student;
import model.StudentP3.StudentProto3;
import org.apache.tomcat.jni.Status;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.stream.Collectors;

@Path("student")
@Api(value = "StudentResource")
public class StudentResource {

    @Inject
    StudentContainer container;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "get list of all students")
    @ApiResponse(code = 200, message = "OK", response = Student[].class)
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
    @ApiOperation(value = "get student with given student card id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Student.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = Response.class),
    })
    public Response getById(@PathParam("studentCardId") int studentCardId) {
        Student student = container.get(studentCardId);

        if (student == null) {
            throw new NotFoundException();
        }

        return Response.ok(student).build();
    }


    @POST
    @JWTNeeded
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "create new student")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Student.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = Response.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = Response.class),
    })
    public Response create(
            @FormParam("name")
                    String name,
            @FormParam("studentCardId")
                    Integer studentCardId,
            @FormParam("faculty")
            @Pattern(regexp = "EAIIB|WIET", message = "faculty must be either EAIIB or WIET") String faculty,
            @FormParam("semester")
                    Integer semester,
            @FormParam("courses")
                    List<String> courses,
            @FormParam("avatar")
                    String avatar,
            @Context UriInfo uriInfo
    ) {
        Student student = new Student(name, studentCardId, faculty, semester, courses, avatar);
        container.addStudent(student);

        System.out.println(student);

        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(studentCardId));
        return Response.created(builder.build()).entity(student).build();
    }


    @PUT
    @Path("{oldStudentCardId}")
    @JWTNeeded
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "edit student with given student card id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Student.class),
            @ApiResponse(code = 400, message = "BAD REQUEST", response = Response.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = Response.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = Response.class),
    })
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
            student.setAvatar(newAvatar);
        }
        container.addStudent(student);

        return student;
    }

    @DELETE
    @JWTNeeded
    @Path("{studentCardId}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "delete student with given student card id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Student.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = Response.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = Response.class),
    })
    public Response delete(@PathParam("studentCardId") int studentCardId) {
        Student student = container.delete(studentCardId);

        if (student == null) {
            throw new NotFoundException();
        }

        return Response.ok(student).build();
    }

    @GET
    @Path("/proto/{studentCardId}")
    @ApiOperation(value = "get student with given student card id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Student.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = Response.class),
    })
    public Response getStudentProto(
            @PathParam("studentCardId")
            @NotNull(message = "studentCardId cannot be null")
            @Pattern(regexp = "^[0-9]{6}$", message = "studentCardId must contain 6 digits")
                    String studentCardId) {
        ProtobufProvider provider = new ProtobufProvider();
        Annotation[] EMPTY_ANNOTATIONS = {};


        Student student = container.get(Integer.parseInt(studentCardId));

        if (student == null) {
            throw new NotFoundException();
        }


        StudentProto3 sp = StudentProto3.newBuilder()
                .setName(student.getName())
                .setSemester(student.getSemester())
                .setFaculty(student.getFaculty())
                .setStudentCardId(student.getStudentCardId())
                .setAvatar(student.getAvatar())
                .addAllCourses(student.getCourses())
                .build();

        final byte[] buf;
        try (final ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            provider.writeTo(sp, sp.getClass(), null, EMPTY_ANNOTATIONS, ProtobufMediaType.MEDIA_TYPE, null, os);
            buf = os.toByteArray();
            return Response.status(
                    Response.Status.OK).entity(buf)
                    .type("application/x-protobuf")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Status.APR_EPROC_UNKNOWN).build();
        }
    }
}
