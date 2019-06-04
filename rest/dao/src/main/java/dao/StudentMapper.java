package dao;

import jpa.Course;
import jpa.Student;
import model.StudentModel;

import java.util.List;
import java.util.stream.Collectors;

public class StudentMapper {
    public static StudentModel toStudentModel(Student student) {
        if (student == null) {
            return null;
        }
        return new StudentModel(
                student.getName(),
                student.getStudentCardId(),
                FacultyMapper.toFacultyModel(student.getFaculty()),
                student.getSemester(),
                CourseMapper.toCourseModels(student.getSchedule()),
                student.getAvatar()
        );
    }

    public static Student toStudent(StudentModel studentModel) {
        List<Course> courses = CourseMapper.toCourses(studentModel.getCourses());

        return new Student(studentModel.getStudentCardId(), studentModel.getName(), studentModel.getSemester(), studentModel.getAvatar(), FacultyMapper.toFaculty(studentModel.getFaculty()), courses);
    }

    public static List<StudentModel> toStudentModels(List<Student> students) {
        return students.stream().map(StudentMapper::toStudentModel).collect(Collectors.toList());
    }
}
