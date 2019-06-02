package studentdao;

import model.Student;
import student.CourseRepository;
import student.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {
    public static Student toStudent(StudentRepository studentRepository) {
        if (studentRepository == null) {
            return null;
        }
        return new Student(
                studentRepository.getName(),
                studentRepository.getStudentCardId(),
                FacultyMapper.toFaculty(studentRepository.getFaculty()),
                studentRepository.getSemester(),
                CourseMapper.toCourses(studentRepository.getSchedule()),
                studentRepository.getAvatar()
        );
    }

    public static StudentRepository toStudentRepository(Student student) {
        List<CourseRepository> courseRepositories = CourseMapper.toCourseRepositories(student.getCourses());

        return new StudentRepository(student.getStudentCardId(), student.getName(), student.getSemester(), student.getAvatar(), FacultyMapper.toFacultyRepository(student.getFaculty()), courseRepositories);
    }

    public static List<Student> toStudents(List<StudentRepository> studentRepositories) {
        return studentRepositories.stream().map(StudentMapper::toStudent).collect(Collectors.toList());
    }
}
