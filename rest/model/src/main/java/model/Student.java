package model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "student")
public class Student {
    private String name;
    private int studentCardId;
    private String faculty;
    private int semester;

    private List<String> courses;

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", studentCardId=" + studentCardId +
                ", faculty='" + faculty + '\'' +
                ", semester=" + semester +
                ", courses=" + courses +
                ", avatar='" + avatar.substring(0, 10) + '\'' +
                '}';
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    private String avatar;

    public Student() {}

    public Student(String name, int studentCardId, String faculty, int semester, List<String> courses, String avatar) {
        this.name = name;
        this.studentCardId = studentCardId;
        this.faculty = faculty;
        this.semester = semester;
        this.courses = courses;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "studentCardId")
    public int getStudentCardId() {
        return studentCardId;
    }

    public void setStudentCardId(int studentCardId) {
        this.studentCardId = studentCardId;
    }

    @XmlElement(name = "faculty")
    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    @XmlElement(name = "semester")
    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }


    public List<String> getCourses() {
        return courses;
    }

    @XmlElement(name = "course")
    public void setCourses(List<String> courses) {
        this.courses = courses;
    }


    @XmlElement(name = "avatar")
    public String getAvatar() {
        return avatar;
    }
}
