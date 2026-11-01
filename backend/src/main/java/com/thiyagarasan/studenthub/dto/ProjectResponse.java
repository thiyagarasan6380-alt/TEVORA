
package com.thiyagarasan.studenthub.dto;

import java.time.LocalDateTime;

public class ProjectResponse {

    private int id;
    private String projectName;
    private String description;
    private String status;
    private int ownerId;
    private String ownerName;
    private LocalDateTime createdAt;

    public ProjectResponse(int id, String projectName,
            String description, String status,
            int ownerId, String ownerName,
            LocalDateTime createdAt) {

        this.id = id;
        this.projectName = projectName;
        this.description = description;
        this.status = status;
        this.ownerId = ownerId;
        this.ownerName = ownerName;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public String getProjectName() {
        return projectName;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
