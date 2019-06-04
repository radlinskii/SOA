package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "faculty")
public class FacultyModel {
    private String name;

    public FacultyModel() {
    }

    public FacultyModel(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Faculty{" +
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
