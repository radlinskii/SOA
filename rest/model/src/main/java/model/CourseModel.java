package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "course")
public class CourseModel {
    private String name;

    private Integer lecturerId;

    public CourseModel(String name, Integer lecturerId) {
        this.name = name;
        this.lecturerId = lecturerId;
    }

    public Integer getLecturerId() {
        return lecturerId;
    }

    public CourseModel() {
    }

    public void setLecturerId(Integer lecturerId) {
        this.lecturerId = lecturerId;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                "lecturerId='" + lecturerId + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
