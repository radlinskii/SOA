package dao;

import jpa.Course;
import model.CourseModel;

import javax.ejb.Stateless;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static dao.CourseMapper.toCourse;

@Stateless
public class CourseDAO extends AbstractDAO {

    private static final Logger LOGGER = Logger.getLogger("SoaLab" + CourseDAO.class.toString());

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Class<Course> getType() {
        return Course.class;
    }

    public CourseModel getByName(String name) {
        return CourseMapper.toCourseModel(super.get(name));
    }

    @SuppressWarnings("unchecked")
    public List<CourseModel> list(int offset, int limit) {
        List<Course> courses = super.list(offset, limit);
        return courses.stream().map(CourseMapper::toCourseModel).collect(Collectors.toList());
    }

    public void create(CourseModel courseModel) {
        super.create(toCourse(courseModel));
    }

}