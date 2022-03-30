package library.avenir.test.service;

import library.avenir.test.dto.student.StudentCreateDto;
import library.avenir.test.dto.student.StudentDto;
import library.avenir.test.dto.student.StudentUpdateDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> findAll();

    StudentDto save(StudentCreateDto studentCreateDto);

    StudentDto getById(Long id);

    StudentDto update(Long id, StudentUpdateDto studentUpdateDto);
}
