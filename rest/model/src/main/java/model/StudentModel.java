package model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "student")
public class StudentModel {
    private String name;
    private Integer studentCardId;
    private FacultyModel faculty;
    private Integer semester;

    private List<CourseModel> courses;

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

    public StudentModel() {
    }

    public StudentModel(String name, Integer studentCardId, FacultyModel faculty, Integer semester, List<CourseModel> courses, String avatar) {
        this.name = name;
        this.studentCardId = studentCardId;
        this.faculty = faculty;
        this.semester = semester;
        this.courses = courses;
        this.avatar = avatar;
    }

    public void update(StudentModel newStudentModel) {
        if (newStudentModel.studentCardId != null) this.setStudentCardId(newStudentModel.studentCardId);
        if (newStudentModel.name != null) this.setName(newStudentModel.name);
        if (newStudentModel.faculty != null) this.setFaculty(newStudentModel.faculty);
        if (newStudentModel.semester != null) this.setSemester(newStudentModel.semester);
        if (newStudentModel.avatar != null) this.setAvatar(newStudentModel.avatar);
        if (newStudentModel.courses != null) this.setCourses(newStudentModel.courses);
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
    public FacultyModel getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyModel faculty) {
        this.faculty = faculty;
    }

    @XmlElement(name = "semester")
    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }


    public List<CourseModel> getCourses() {
        return courses;
    }

    @XmlElement(name = "course")
    public void setCourses(List<CourseModel> courseModels) {
        this.courses = courseModels;
    }


    @XmlElement(name = "avatar")
    public String getAvatar() {
        return avatar;
    }
}
