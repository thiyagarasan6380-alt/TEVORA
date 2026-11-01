package com.thiyagarasan.studenthub.dto;

import java.time.LocalDateTime;

public class TeamMemberResponse {

    private int id;
    private int studentId;
    private String studentName;
    private LocalDateTime joinedAt;

    public TeamMemberResponse(
            int id, int studentId,
            String studentName, LocalDateTime joinedAt) {

        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.joinedAt = joinedAt;
    }

    public int getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }
}