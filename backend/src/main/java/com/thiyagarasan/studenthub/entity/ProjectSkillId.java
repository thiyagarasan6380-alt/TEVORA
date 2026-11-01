package com.thiyagarasan.studenthub.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ProjectSkillId implements Serializable {

    @Column(name = "project_id")
    private int projectId;

    @Column(name = "skill_id")
    private int skillId;

    public ProjectSkillId() {
    }

    public ProjectSkillId(int projectId, int skillId) {
        this.projectId = projectId;
        this.skillId = skillId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getSkillId() {
        return skillId;
    }

    public void setSkillId(int skillId) {
        this.skillId = skillId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof ProjectSkillId))
            return false;

        ProjectSkillId other = (ProjectSkillId) obj;

        return projectId == other.projectId &&
                skillId == other.skillId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, skillId);
    }
}