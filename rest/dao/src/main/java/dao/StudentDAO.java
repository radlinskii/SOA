package dao;

import jpa.StudentRepository;
import model.Student;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class StudentDAO extends AbstractDao {

    private static final Logger LOGGER = Logger.getLogger("SoaLab" + StudentDAO.class.toString());

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Class<StudentRepository> getType() {
        return StudentRepository.class;
    }

    public Student get(Integer index) {
        return StudentMapper.toStudent(super.get(index));
    }

    @SuppressWarnings("unchecked")
    public List<Student> list(int offset, int limit) {
        List<StudentRepository> studentRepositories = super.list(offset, limit);
        return StudentMapper.toStudents(studentRepositories);
    }

    public void create(Student student) {
        super.create(StudentMapper.toStudentRepository(student));
    }


    public List<Student> getStudentsBySemester(Integer semester) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<StudentRepository> query = cb.createQuery(StudentRepository.class);
        Root<StudentRepository> studentRepositoryRoot = query.from(StudentRepository.class);

        query.where(cb.equal(studentRepositoryRoot.get("semester"), semester));

        return StudentMapper.toStudents(entityManager.createQuery(query).getResultList());
    }
}