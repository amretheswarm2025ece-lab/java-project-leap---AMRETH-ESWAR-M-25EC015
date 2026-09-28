package com.classroom.attendance.controller;

import com.classroom.attendance.entity.Teacher;
import com.classroom.attendance.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    // Add teacher
    @PostMapping
    public Teacher addTeacher(@RequestBody Teacher teacher) {
        return teacherService.addTeacher(teacher);
    }

    // Get all teachers
    @GetMapping
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    // Get teacher by ID
    @GetMapping("/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Long id) {

        return teacherService.getTeacherById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update teacher
    @PutMapping("/{id}")
    public Teacher updateTeacher(
            @PathVariable Long id,
            @RequestBody Teacher teacher) {

        return teacherService.updateTeacher(id, teacher);
    }

    // Delete teacher
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable Long id) {

        boolean deleted = teacherService.deleteTeacher(id);

        if (!deleted) {
            return ResponseEntity
                    .status(404)
                    .body("Teacher not found");
        }

        return ResponseEntity
                .ok("Teacher deleted successfully");
    }
}