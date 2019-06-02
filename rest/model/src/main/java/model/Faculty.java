package model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "faculty")
public class Faculty {
    private String name;

    public Faculty() {
    }

    public Faculty(String name) {
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
