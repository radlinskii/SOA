package studentdao;

import model.Course;
import student.CourseRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CourseMapper {
    public static Course toCourse(CourseRepository courseRepository) {
        return new Course(courseRepository.getName());
    }

    public static List<Course> toCourses(List<CourseRepository> courseRepositories) {
        return courseRepositories.stream().map(CourseMapper::toCourse).collect(Collectors.toList());
    }

    public static CourseRepository toCourseRepository(Course course) {
        return new CourseRepository(course.getName());
    }

    public static List<CourseRepository> toCourseRepositories(List<Course> courses) {
        return courses.stream().map(CourseMapper::toCourseRepository).collect(Collectors.toList());
    }

}
