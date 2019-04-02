package soap;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import models.student.Student;
import models.student.StudentContainer;
import org.jboss.annotation.security.SecurityDomain;
import org.jboss.ws.api.annotation.WebContext;

import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
@WebService
@SecurityDomain("my-domain")
@DeclareRoles({"users", "guests"})
@WebContext(authMethod="BASIC",transportGuarantee="NONE")
public class StudentController {

    @Inject
    StudentContainer container;

    @WebMethod
    @RolesAllowed("users")
    @XmlElement(name = "Student")
    public Student addStudent(@WebParam(name = "name") String name,
                              @WebParam(name = "studentCardId") int studentCardId,
                              @WebParam(name = "faculty") String faculty,
                              @WebParam(name = "semester") @XmlElement(required = true) int semester,
                              @WebParam(name = "courses") List<String> courses,
                              @WebParam(name = "avatar") String avatar) {
        String encodedAvatar = avatar == null ? null : Base64.encode(avatar.getBytes());
        Student student = new Student(name, studentCardId, faculty, semester, courses, encodedAvatar);
        container.addStudent(student);

        return student;
    }


    @WebMethod
    @PermitAll
    @XmlElement(name = "Student")
    public Student getStudentById(@WebParam(name = "studentCardId") int studentCardId) {
        return container.get(studentCardId);
    }

    @WebMethod
    @PermitAll
    @XmlElement(name = "Student")
    public Student editStudent(@WebParam(name = "studentCardId") int oldStudentCardId,
                               @WebParam(name = "newName") String newName,
                               @WebParam(name = "newStudentCardId") Integer newStudentCardId,
                               @WebParam(name = "newFaculty") String newFaculty,
                               @WebParam(name = "newSemester") Integer newSemester,
                               @WebParam(name = "newCourses") List<String> newCourses,
                               @WebParam(name = "newAvatar") String newAvatar) {
        Student student = container.get(oldStudentCardId);

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
            String encodedNewAvatar = Base64.encode(newAvatar.getBytes());
            student.setAvatar(encodedNewAvatar);

        }
        container.addStudent(student);

        return student;
    }


    @WebMethod
    @PermitAll
    @XmlElementWrapper(name = "StudentList")
    @XmlElement(name = "Student")
    public List<Student> list(@WebParam(name = "facultyFilter") String facultyFilter,
                              @WebParam(name = "courseFilter") String courseFilter) {

        List<Student> students = container.all();

        if (!facultyFilter.isEmpty()) {
            students = students.stream().filter(s -> s.getFaculty().equals(facultyFilter)).collect(Collectors.toList());
        }

        if (!courseFilter.isEmpty()) {
            students = students.stream().filter(s -> s.getCourses().contains(courseFilter)).collect(Collectors.toList());
        }

        return students;
    }

}
