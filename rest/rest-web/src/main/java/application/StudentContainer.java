package application;

import model.Student;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class StudentContainer {

    private Map<Integer, Student> students;

    @PostConstruct
    public void init() {
        students = new HashMap<>();
    }

    public void addStudent(Student s) {
        students.put(s.getStudentCardId(), s);
    }

    public List<Student> all() {
        return new ArrayList<>(students.values());
    }

    public Student get(Integer id) {
        return students.get(id);
    }

    public Student delete(Integer id) {
        return students.remove(id);
    }
}
