package dao;

import jpa.Course;
import model.CourseModel;

import java.util.List;
import java.util.stream.Collectors;

public class CourseMapper {
    public static CourseModel toCourseModel(Course course) {
        return new CourseModel(course.getName(), course.getLecturerId());
    }

    public static List<CourseModel> toCourseModels(List<Course> courses) {
        return courses.stream().map(CourseMapper::toCourseModel).collect(Collectors.toList());
    }

    public static Course toCourse(CourseModel courseModel) {
        return new Course(courseModel.getName(), courseModel.getLecturerId());
    }

    public static List<Course> toCourses(List<CourseModel> courseModels) {
        return courseModels.stream().map(CourseMapper::toCourse).collect(Collectors.toList());
    }

}
