package com.classroom.attendance.service;

import com.classroom.attendance.entity.Teacher;
import com.classroom.attendance.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    // Add teacher
    public Teacher addTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    // Get all teachers
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    // Get teacher by ID
    public Optional<Teacher> getTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    // Update teacher
    public Teacher updateTeacher(Long id, Teacher teacher) {

        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        existingTeacher.setName(teacher.getName());
        existingTeacher.setEmail(teacher.getEmail());
        existingTeacher.setDepartment(teacher.getDepartment());

        return teacherRepository.save(existingTeacher);
    }

    // Delete teacher
    public boolean deleteTeacher(Long id) {

        if (!teacherRepository.existsById(id)) {
            return false;
        }

        teacherRepository.deleteById(id);
        return true;
    }
}