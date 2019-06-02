package dao;

import jpa.CourseRepository;
import model.Course;

import java.util.List;
import java.util.stream.Collectors;

public class CourseMapper {
    public static Course toCourse(CourseRepository courseRepository) {
        return new Course(courseRepository.getName(), courseRepository.getLecturerId());
    }

    public static List<Course> toCourses(List<CourseRepository> courseRepositories) {
        return courseRepositories.stream().map(CourseMapper::toCourse).collect(Collectors.toList());
    }

    public static CourseRepository toCourseRepository(Course course) {
        return new CourseRepository(course.getName(), course.getLecturerId());
    }

    public static List<CourseRepository> toCourseRepositories(List<Course> courses) {
        return courses.stream().map(CourseMapper::toCourseRepository).collect(Collectors.toList());
    }

}
