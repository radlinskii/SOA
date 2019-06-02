package jpa;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course")
public class CourseRepository {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "lecturer_id")
    private Integer lecturerId;

    @ManyToMany(mappedBy = "schedule")
    private List<StudentRepository> students;

    public CourseRepository() {
    }

    public CourseRepository(String name, Integer lecturerId) {
        this.name = name;
        this.lecturerId = lecturerId;
    }

    public Integer getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(Integer lecturerId) {
        this.lecturerId = lecturerId;
    }

    public List<StudentRepository> getStudents() {
        return students;
    }

    public void setStudents(List<StudentRepository> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}