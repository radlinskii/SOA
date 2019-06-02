package studentdao;

import model.Faculty;
import student.FacultyRepository;

import javax.ejb.Stateless;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class FacultyDAO extends AbstractDao {

    private static final Logger LOGGER = Logger.getLogger("SoaLab" + FacultyDAO.class.toString());

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Class<FacultyRepository> getType() {
        return FacultyRepository.class;
    }

    public Faculty get(Integer index) {
        return FacultyMapper.toFaculty(super.get(index));
    }

    @SuppressWarnings("unchecked")
    public List<Faculty> list(int offset, int limit) {
        List<FacultyRepository> facultyRepositories = super.list(offset, limit);
        return facultyRepositories.stream().map(FacultyMapper::toFaculty).collect(Collectors.toList());
    }

    public void create(Faculty faculty) {
        super.create(FacultyMapper.toFacultyRepository(faculty));
    }

}