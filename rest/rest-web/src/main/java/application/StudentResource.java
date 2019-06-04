package application;

import authorization.JWTNeeded;
import dao.CourseDAO;
import dao.FacultyDAO;
import dao.StudentDAO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import model.CourseModel;
import model.FacultyModel;
import model.StudentModel;

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

    @EJB
    CourseDAO courseDAO;

    @EJB
    FacultyDAO facultyDAO;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "get list of all students")
    @ApiResponse(code = 200, message = "OK", response = StudentModel[].class)
    public Response list(@QueryParam("semester") Integer semesterFilter, @QueryParam("faculty") String facultyFilter, @QueryParam("course") String courseFilter) {


        List<StudentModel> studentModels = studentDAO.getStudents(semesterFilter, facultyFilter, courseFilter);

        return Response.ok(studentModels).build();
    }

    @GET
    @Path("{studentCardId}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "get student with given student card id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = StudentModel.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = Response.class),
    })
    public Response getById(@PathParam("studentCardId") int studentCardId) {
        StudentModel studentModel = studentDAO.get(studentCardId);

        if (studentModel == null) {
            throw new NotFoundException();
        }

        return Response.ok(studentModel).build();
    }


    @POST
    @JWTNeeded
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "create new student")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = StudentModel.class),
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
                    String facultyName,
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

        ArrayList<CourseModel> courseModels = getCourses(coursesNames);

        FacultyModel facultyModel = getFaculty(facultyName);


        StudentModel studentModel = new StudentModel(name, studentCardId, facultyModel, semester, courseModels, avatar);
        try {
            studentDAO.create(studentModel);
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).build();
        }


        UriBuilder builder = uriInfo.getAbsolutePathBuilder();
        builder.path(Integer.toString(studentCardId));
        return Response.created(builder.build()).entity(studentModel).build();
    }


    @PUT
    @Path("{oldStudentCardId}")
    @JWTNeeded
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "edit student with given student card id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = StudentModel.class),
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
        StudentModel studentModel = studentDAO.get(oldStudentCardId);
        if (studentModel == null) {
            throw new NotFoundException();
        }

        studentDAO.delete(oldStudentCardId);

        ArrayList<CourseModel> courseModels = getCourses(newCourses);

        FacultyModel facultyModel = getFaculty(newFaculty);


        studentModel.update(new StudentModel(newName, newStudentCardId, facultyModel, newSemester, courseModels, newAvatar));
        try {
            studentDAO.create(studentModel);
        } catch (Exception e) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        return Response.ok(studentModel).build();
    }

    @DELETE
    @JWTNeeded
    @Path("{studentCardId}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "delete student with given student card id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = StudentModel.class),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = Response.class),
            @ApiResponse(code = 404, message = "NOT FOUND", response = Response.class),
    })
    public Response delete(@PathParam("studentCardId") int studentCardId) {
        StudentModel studentModel = studentDAO.get(studentCardId);

        if (studentModel == null) {
            throw new NotFoundException();
        }

        studentDAO.delete(studentCardId);

        return Response.ok(studentModel).build();
    }

    private ArrayList<CourseModel> getCourses(List<String> newCourses) {
        ArrayList<CourseModel> courseModels = new ArrayList<>();
        for (String courseName : newCourses) {

            try {
                CourseModel courseModel = courseDAO.getByName(courseName);

                courseModels.add(courseModel);
            } catch (Exception e) {
                throw new BadRequestException();
            }
        }

        return courseModels;
    }

    private FacultyModel getFaculty(String newFaculty) {
        try {
            return facultyDAO.getByName(newFaculty);

        } catch (Exception e) {
            throw new BadRequestException();
        }
    }
}