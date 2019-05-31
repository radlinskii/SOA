package application;

import authorization.JWTNeeded;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import model.Course;
import model.Student;
import studentdao.StudentDAO;

import javax.ejb.EJB;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;

@Path("student")
@Api(value = "StudentResource")
public class StudentResource {

    @EJB
    StudentDAO studentDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "get list of all students")
    @ApiResponse(code = 200, message = "OK", response = Student[].class)
    public Response list(@QueryParam("semester") Integer semesterFilter) {

        List<Student> students = semesterFilter == null ? studentDAO.list(0, 100) : studentDAO.getStudentsBySemester(semesterFilter);

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
        Student student = studentDAO.get(studentCardId);

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
            @ApiResponse(code = 409, message = "CONFLICT", response = Response.class),
    })
    public Response create(
            @NotNull
            @FormParam("name")
                    String name,
            @NotNull
            @FormParam("studentCardId")
                    Integer studentCardId,
            @NotNull
            @FormParam("faculty")
            @Pattern(regexp = "EAIIB|WIET", message = "faculty must be either EAIIB or WIET")
                    String faculty,
            @NotNull
            @FormParam("semester")
                    Integer semester,
            @NotNull
            @FormParam("courses")
                    List<String> coursesNames,
            @NotNull
            @FormParam("avatar")
                    String avatar,
            @Context UriInfo uriInfo
    ) {

        ArrayList<Course> courses = new ArrayList<>();
        for (String courseName : coursesNames) {
            courses.add(new Course(courseName));
        }

        Student student = new Student(name, studentCardId, faculty, semester, courses, avatar);
        try {
            studentDAO.create(student);
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).build();
        }


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
            @ApiResponse(code = 409, message = "CONFLICT", response = Response.class),
    })
    public Response editStudent(
            @PathParam("oldStudentCardId") int oldStudentCardId,
            @FormParam("name") String newName,
            @FormParam("studentCardId") Integer newStudentCardId,
            @FormParam("faculty") String newFaculty,
            @FormParam("semester") Integer newSemester,
            @FormParam("courses") List<String> newCourses,
            @FormParam("avatar") String newAvatar
    ) {
        Student student = studentDAO.get(oldStudentCardId);
        if (student == null) {
            throw new NotFoundException();
        }

        studentDAO.delete(oldStudentCardId);

        ArrayList<Course> courses = new ArrayList<>();
        for (String courseName : newCourses) {
            courses.add(new Course(courseName));
        }

        student.update(new Student(newName, newStudentCardId, newFaculty, newSemester, courses, newAvatar));
        try {
            studentDAO.create(student);
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        return Response.ok(student).build();
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
        Student student = studentDAO.get(studentCardId);

        if (student == null) {
            throw new NotFoundException();
        }

        studentDAO.delete(studentCardId);

        return Response.ok(student).build();
    }
}

// TODO 1x oneToMany
// TODO 1 x two-way relation ? OneToOne?
// TODO min. 4 tables
