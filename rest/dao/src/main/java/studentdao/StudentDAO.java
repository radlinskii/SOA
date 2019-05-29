package studentdao;

import model.Student;
import student.StudentRepository;

import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class StudentDAO extends AbstractDao {

    private static final Logger LOGGER = Logger.getLogger("SoaLab" + StudentDAO.class.toString());

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Class<StudentRepository> getType() {
        return StudentRepository.class;
    }

    public Student get(Integer index) {
        return toStudent(super.get(index));
    }

    @SuppressWarnings("unchecked")
    public List<Student> list(int offset, int limit) {
        List<StudentRepository> studentRepositories = super.list(offset, limit);
        return studentRepositories.stream().map(this::toStudent).collect(Collectors.toList());
    }

    public void create(Student student) {
        super.create(toStudentRepository(student));
    }

    private Student toStudent(StudentRepository studentRepository) {
        if (studentRepository == null) {
            return null;
        }
        return new Student(
                studentRepository.getName(),
                studentRepository.getStudentCardId(),
                studentRepository.getFaculty(),
                studentRepository.getSemester(),
                new ArrayList<>(),
                studentRepository.getAvatar()
        );
    }

    private StudentRepository toStudentRepository(Student student) {
        return new StudentRepository(student.getStudentCardId(), student.getName(), student.getSemester(), student.getAvatar(), student.getFaculty());
    }
}