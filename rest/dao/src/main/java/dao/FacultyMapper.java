package dao;

import jpa.Faculty;
import model.FacultyModel;

import java.util.List;
import java.util.stream.Collectors;

public class FacultyMapper {
    public static FacultyModel toFacultyModel(Faculty faculty) {
        return new FacultyModel(faculty.getName());
    }

    public static List<FacultyModel> toFacultyModels(List<Faculty> faculty) {
        return faculty.stream().map(FacultyMapper::toFacultyModel).collect(Collectors.toList());
    }

    public static Faculty toFaculty(FacultyModel facultyModel) {
        return new Faculty(facultyModel.getName());
    }

    public static List<Faculty> toFaculties(List<FacultyModel> faculties) {
        return faculties.stream().map(FacultyMapper::toFaculty).collect(Collectors.toList());
    }

}
