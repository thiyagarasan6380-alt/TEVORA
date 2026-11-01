package com.thiyagarasan.studenthub.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentSkillId implements Serializable {

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "skill_id")
    private int skillId;

    public StudentSkillId() {
    }

    public StudentSkillId(int studentId, int skillId) {
        this.studentId = studentId;
        this.skillId = skillId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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
        if (!(obj instanceof StudentSkillId))
            return false;

        StudentSkillId other = (StudentSkillId) obj;

        return studentId == other.studentId &&
                skillId == other.skillId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, skillId);
    }
}