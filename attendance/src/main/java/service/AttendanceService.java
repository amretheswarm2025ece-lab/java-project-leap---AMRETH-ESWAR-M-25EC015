package com.classroom.attendance.service;

import com.classroom.attendance.entity.Attendance;
import com.classroom.attendance.repository.AttendanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;

    public AttendanceService(
            AttendanceRepository attendanceRepository) {

        this.attendanceRepository =
                attendanceRepository;
    }


    // ==================================================
    // MARK ATTENDANCE
    // ==================================================

    public Attendance markAttendance(
            Attendance attendance) {

        boolean alreadyExists =
                attendanceRepository
                        .existsByStudentIdAndDate(
                                attendance.getStudentId(),
                                attendance.getDate()
                        );

        if (alreadyExists) {

            throw new RuntimeException(
                    "Attendance already marked for this student on this date"
            );
        }

        return attendanceRepository.save(
                attendance
        );
    }


    // ==================================================
    // GET ALL ATTENDANCE
    // ==================================================

    public List<Attendance> getAllAttendance() {

        return attendanceRepository.findAll();
    }


    // ==================================================
    // GET ATTENDANCE BY ID
    // ==================================================

    public Optional<Attendance> getAttendanceById(
            Long id) {

        return attendanceRepository.findById(id);
    }


    // ==================================================
    // UPDATE ATTENDANCE
    // ==================================================

    public Attendance updateAttendance(
            Long id,
            Attendance attendance) {

        Attendance existingAttendance =
                attendanceRepository.findById(id)
                        .orElseThrow(
                                () -> new RuntimeException(
                                        "Attendance not found"
                                )
                        );


        existingAttendance.setStudentId(
                attendance.getStudentId()
        );


        existingAttendance.setDate(
                attendance.getDate()
        );


        existingAttendance.setStatus(
                attendance.getStatus()
        );


        return attendanceRepository.save(
                existingAttendance
        );
    }


    // ==================================================
    // DELETE ATTENDANCE
    // ==================================================

    public boolean deleteAttendance(Long id) {

        if (!attendanceRepository.existsById(id)) {

            return false;
        }


        attendanceRepository.deleteById(id);

        return true;
    }
}