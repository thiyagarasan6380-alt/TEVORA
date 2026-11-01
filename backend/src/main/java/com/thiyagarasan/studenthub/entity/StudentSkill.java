package com.thiyagarasan.studenthub.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "student_skills")
public class StudentSkill {

    @EmbeddedId
    private StudentSkillId id = new StudentSkillId();

    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @MapsId("skillId")
    @JoinColumn(name = "skill_id")
    private Skill skill;

    public StudentSkill() {
    }

    public StudentSkill(Student student, Skill skill) {
        this.student = student;
        this.skill = skill;
    }

    public StudentSkillId getId() {
        return id;
    }

    public void setId(StudentSkillId id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }
}