package jpa;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="student")
public class Student {

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
    private Faculty faculty;

    @ManyToMany
    @JoinTable(
            name = "schedule",
            joinColumns = {@JoinColumn(name = "studentCardId")},
            inverseJoinColumns = {@JoinColumn(name = "course_name")}
    )
    private List<Course> schedule;

    public Student() {
    }

    public Student(int studentCardId, String name, int semester, String avatar, Faculty faculty, List<Course> schedule) {
        this.studentCardId = studentCardId;
        this.name = name;
        this.semester = semester;
        this.avatar = avatar;
        this.faculty = faculty;
        this.schedule = schedule;
    }

    public List<Course> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Course> schedule) {
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

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
