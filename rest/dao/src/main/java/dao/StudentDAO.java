package dao;

import jpa.Course;
import jpa.Faculty;
import jpa.Student;
import model.StudentModel;

import javax.ejb.Stateless;
import javax.persistence.criteria.*;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class StudentDAO extends AbstractDAO {

    private static final Logger LOGGER = Logger.getLogger("SoaLab" + StudentDAO.class.toString());

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Class<Student> getType() {
        return Student.class;
    }

    public StudentModel get(Integer index) {
        return StudentMapper.toStudentModel(super.get(index));
    }

    @SuppressWarnings("unchecked")
    public List<StudentModel> list(int offset, int limit) {
        List<Student> students = super.list(offset, limit);
        return StudentMapper.toStudentModels(students);
    }

    public void create(StudentModel studentModel) {
        super.create(StudentMapper.toStudent(studentModel));
    }


    public List<StudentModel> getStudents(Integer semester, String faculty, String course) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> studentRoot = query.from(Student.class);

        Join<Faculty, Student> faculties = studentRoot.join("faculty");
        Join<Student, Course> schedule = studentRoot.join("schedule");


        List<Predicate> predList = new LinkedList<>();

        if (course != null) {
            predList.add(cb.equal(schedule.get("name"), course));
        }

        if (semester != null) {
            predList.add(cb.equal(studentRoot.get("semester"), semester));
        }

        if (faculty != null) {
            predList.add(cb.equal(faculties.get("name"), faculty));
        }

        Predicate[] predArray = new Predicate[predList.size()];
        predList.toArray(predArray);
        query.where(predArray);

        return StudentMapper.toStudentModels(entityManager.createQuery(query).getResultList());
    }
}