package application;

import model.StudentModel;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class StudentContainer {

    private Map<Integer, StudentModel> students;

    @PostConstruct
    public void init() {
        students = new HashMap<>();
    }

    public void addStudent(StudentModel s) {
        students.put(s.getStudentCardId(), s);
    }

    public List<StudentModel> all() {
        return new ArrayList<>(students.values());
    }

    public StudentModel get(Integer id) {
        return students.get(id);
    }

    public StudentModel delete(Integer id) {
        return students.remove(id);
    }
}
