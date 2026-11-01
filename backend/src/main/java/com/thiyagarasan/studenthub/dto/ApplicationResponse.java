
package com.thiyagarasan.studenthub.dto;

import java.time.LocalDateTime;

public class ApplicationResponse {

    private int id;
    private int studentId;
    private String studentName;
    private int projectId;
    private String projectName;
    private String status;
    private LocalDateTime appliedAt;

    public ApplicationResponse(
            int id,
            int studentId,
            String studentName,
            int projectId,
            String projectName,
            String status,
            LocalDateTime appliedAt) {

        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.projectId = projectId;
        this.projectName = projectName;
        this.status = status;
        this.appliedAt = appliedAt;
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

    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getAppliedAt() {
        return appliedAt;
    }
}
