package model;

public class Project {
    private String projectName;
    private String requiredSkill;

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setRequiredSkill(String requiredSkill) {
        this.requiredSkill = requiredSkill;
    }

    public String getRequiredSkill() {
        return requiredSkill;
    }
}