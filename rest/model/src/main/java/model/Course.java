package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "course")
public class Course {
    private String name;

    public Course() {
    }

    public Course(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
