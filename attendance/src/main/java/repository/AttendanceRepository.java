package com.classroom.attendance.repository;

import com.classroom.attendance.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface AttendanceRepository
        extends JpaRepository<Attendance, Long> {

    boolean existsByStudentIdAndDate(
            Long studentId,
            LocalDate date
    );
}