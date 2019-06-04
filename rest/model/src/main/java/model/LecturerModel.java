package model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "lecturer")
public class LecturerModel {
    private Integer id;

    private String name;

    private String title;

    private List<CourseModel> courses;

    public LecturerModel() {
    }

    public LecturerModel(Integer id, String name, String title, List<CourseModel> courses) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.courses = courses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CourseModel> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseModel> courses) {
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
