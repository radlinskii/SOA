package student;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class CourseRepository {

    @Id
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "schedule")
    private List<StudentRepository> students;

    public CourseRepository() {
    }

    public CourseRepository(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
