package student;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="student")
public class StudentRepository {

    @Id
    @Column(name = "studentCardId")
    private int studentCardId;

    @Column(name = "name")
    private String name;

    @Column(name = "semester")
    private int semester;

    @Column(name = "avatar")
    private String avatar;

    @ManyToOne
    @JoinColumn(name = "faculty")
    private FacultyRepository faculty;

    @ManyToMany
    @JoinTable(
            name = "schedule",
            joinColumns = {@JoinColumn(name = "studentCardId")},
            inverseJoinColumns = {@JoinColumn(name = "course_name")}
    )
    private List<CourseRepository> schedule;

    public StudentRepository() {}

    public StudentRepository(int studentCardId, String name, int semester, String avatar, FacultyRepository faculty, List<CourseRepository> schedule) {
        this.studentCardId = studentCardId;
        this.name = name;
        this.semester = semester;
        this.avatar = avatar;
        this.faculty = faculty;
        this.schedule = schedule;
    }

    public List<CourseRepository> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<CourseRepository> schedule) {
        this.schedule = schedule;
    }

    public int getStudentCardId() {
        return studentCardId;
    }

    public void setStudentCardId(int studentCardId) {
        this.studentCardId = studentCardId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public FacultyRepository getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyRepository faculty) {
        this.faculty = faculty;
    }
}
