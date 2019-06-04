package dao;

import jpa.Faculty;
import model.FacultyModel;

import javax.ejb.Stateless;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Stateless
public class FacultyDAO extends AbstractDAO {

    private static final Logger LOGGER = Logger.getLogger("SoaLab" + FacultyDAO.class.toString());

    @Override
    protected Logger getLogger() {
        return LOGGER;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Class<Faculty> getType() {
        return Faculty.class;
    }

    public FacultyModel getByName(String name) {
        return FacultyMapper.toFacultyModel(super.get(name));
    }

    @SuppressWarnings("unchecked")
    public List<FacultyModel> list(int offset, int limit) {
        List<Faculty> faculties = super.list(offset, limit);
        return faculties.stream().map(FacultyMapper::toFacultyModel).collect(Collectors.toList());
    }

    public void create(FacultyModel facultyModel) {
        super.create(FacultyMapper.toFaculty(facultyModel));
    }

}