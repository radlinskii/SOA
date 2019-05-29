package student;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

    @Column(name = "faculty")
    private String faculty;

    public StudentRepository() {}

    public StudentRepository(int studentCardId, String name, int semester, String avatar, String faculty) {
        this.studentCardId = studentCardId;
        this.name = name;
        this.semester = semester;
        this.avatar = avatar;
        this.faculty = faculty;
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

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
