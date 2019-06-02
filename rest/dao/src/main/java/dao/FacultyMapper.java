package dao;

import jpa.FacultyRepository;
import model.Faculty;

import java.util.List;
import java.util.stream.Collectors;

public class FacultyMapper {
    public static Faculty toFaculty(FacultyRepository facultyRepository) {
        return new Faculty(facultyRepository.getName());
    }

    public static List<Faculty> toFaculties(List<FacultyRepository> facultyRepositories) {
        return facultyRepositories.stream().map(FacultyMapper::toFaculty).collect(Collectors.toList());
    }

    public static FacultyRepository toFacultyRepository(Faculty faculty) {
        return new FacultyRepository(faculty.getName());
    }

    public static List<FacultyRepository> toFacultyRepositories(List<Faculty> faculties) {
        return faculties.stream().map(FacultyMapper::toFacultyRepository).collect(Collectors.toList());
    }

}
