package model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "student")
public class Student {
    private String name;
    private Integer studentCardId;
    private String faculty;
    private Integer semester;

    private List<Course> courses;

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

    public Student(String name, Integer studentCardId, String faculty, Integer semester, List<Course> courses, String avatar) {
        this.name = name;
        this.studentCardId = studentCardId;
        this.faculty = faculty;
        this.semester = semester;
        this.courses = courses;
        this.avatar = avatar;
    }

    public void update(Student newStudent) {
        if (newStudent.studentCardId != null) this.setStudentCardId(newStudent.studentCardId);
        if (newStudent.name != null) this.setName(newStudent.name);
        if (newStudent.faculty != null) this.setFaculty(newStudent.faculty);
        if (newStudent.semester != null) this.setSemester(newStudent.semester);
        if (newStudent.avatar != null) this.setAvatar(newStudent.avatar);
        if (newStudent.courses != null) this.setCourses(newStudent.courses);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "studentCardId")
    public Integer getStudentCardId() {
        return studentCardId;
    }

    public void setStudentCardId(Integer studentCardId) {
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
    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }


    public List<Course> getCourses() {
        return courses;
    }

    @XmlElement(name = "course")
    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }


    @XmlElement(name = "avatar")
    public String getAvatar() {
        return avatar;
    }
}
