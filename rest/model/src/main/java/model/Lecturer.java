package model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "lecturer")
public class Lecturer {
    private Integer id;

    private String name;

    private String title;

    private List<Course> courses;

    public Lecturer() {
    }

    public Lecturer(Integer id, String name, String title, List<Course> courses) {
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

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
