package main.java.com.asd.session.domain.model.person;

import java.time.LocalDateTime;

public class Attendance {
    private AttendanceId attendanceId;
    private AttendanceStatus currentAttendanceStatus;
    private LocalDateTime lastModified;

    public Attendance(AttendanceId attendanceId, AttendanceStatus currentAttendanceStatus, LocalDateTime lastModified) {
        this.attendanceId = attendanceId;
        this.currentAttendanceStatus = currentAttendanceStatus;
        this.lastModified = lastModified;
    }
}
