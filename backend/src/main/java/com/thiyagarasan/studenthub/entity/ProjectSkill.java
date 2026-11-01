package com.thiyagarasan.studenthub.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "project_skills")
public class ProjectSkill {

    @EmbeddedId
    private ProjectSkillId id = new ProjectSkillId();

    @ManyToOne
    @MapsId("projectId")
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @MapsId("skillId")
    @JoinColumn(name = "skill_id")
    private Skill skill;

    public ProjectSkill() {
    }

    public ProjectSkill(Project project, Skill skill) {
        this.project = project;
        this.skill = skill;
    }

    public ProjectSkillId getId() {
        return id;
    }

    public void setId(ProjectSkillId id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}