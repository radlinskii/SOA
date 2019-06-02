package jpa;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lecturer")
public class LecturerRepository {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "title")
    private String title;

    @OneToMany
    @JoinColumn(name = "name")
    private List<CourseRepository> classes;


    public LecturerRepository(Integer id, String name, String title, List<CourseRepository> classes) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.classes = classes;
    }

    public LecturerRepository() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CourseRepository> getClasses() {
        return classes;
    }

    public void setClasses(List<CourseRepository> classes) {
        this.classes = classes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


