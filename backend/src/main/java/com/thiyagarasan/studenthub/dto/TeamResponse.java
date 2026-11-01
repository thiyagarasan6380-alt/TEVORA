
package com.thiyagarasan.studenthub.dto;

public class TeamResponse {

    private int id;
    private int projectId;
    private String projectName;

    public TeamResponse(int id, int projectId, String projectName) {
        this.id = id;
        this.projectId = projectId;
        this.projectName = projectName;
    }

    public int getId() {
        return id;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getProjectName() {
        return projectName;
    }
}
