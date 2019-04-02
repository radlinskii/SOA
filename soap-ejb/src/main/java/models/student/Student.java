package models.student;

import java.util.List;

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
                ", avatar='" + avatar + '\'' +
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

    public int getStudentCardId() {
        return studentCardId;
    }

    public void setStudentCardId(int studentCardId) {
        this.studentCardId = studentCardId;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public List<String> getCourses() {
        return courses;
    }

    public void setCourses(List<String> courses) {
        this.courses = courses;
    }

    public String getAvatar() {
        return avatar;
    }
}
