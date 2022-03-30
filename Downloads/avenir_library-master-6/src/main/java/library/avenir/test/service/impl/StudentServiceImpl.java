package library.avenir.test.service.impl;

import library.avenir.test.dto.student.StudentCreateDto;
import library.avenir.test.dto.student.StudentDto;
import library.avenir.test.dto.student.StudentUpdateDto;
import library.avenir.test.entity.Student;
import library.avenir.test.mapper.StudentMapper;
import library.avenir.test.repository.StudentRepository;
import library.avenir.test.service.StudentService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository,
                              StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public List<StudentDto> findAll() {
        return studentRepository.findAll().stream()
                .map(studentMapper::toStudentDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto save(StudentCreateDto studentCreateDto) {
        Student student = studentMapper.toStudent(studentCreateDto);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toStudentDto(savedStudent);
    }

    @Override
    public StudentDto getById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no Student with id " + id));
        return studentMapper.toStudentDto(student);
    }

    @Override
    public StudentDto update(Long id, StudentUpdateDto studentUpdateDto) {
        Student updatingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("There is no Student with id " + id));
        Student student = studentMapper.toStudent(updatingStudent, studentUpdateDto);
        Student updatedStudent = studentRepository.save(student);
        return studentMapper.toStudentDto(updatedStudent);
    }
}
