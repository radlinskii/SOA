package studentdao;

import model.Course;
import student.CourseRepository;

import javax.ejb.Stateless;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static studentdao.CourseMapper.toCourseRepository;

@Stateless
public class CourseDAO extends AbstractDao {

    private static final Logger LOGGER = Logger.getLogger("SoaLab" + CourseDAO.class.toString());

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Class<CourseRepository> getType() {
        return CourseRepository.class;
    }

    public Course get(Integer index) {
        return CourseMapper.toCourse(super.get(index));
    }

    @SuppressWarnings("unchecked")
    public List<Course> list(int offset, int limit) {
        List<CourseRepository> courseRepositories = super.list(offset, limit);
        return courseRepositories.stream().map(CourseMapper::toCourse).collect(Collectors.toList());
    }

    public void create(Course course) {
        super.create(toCourseRepository(course));
    }

}