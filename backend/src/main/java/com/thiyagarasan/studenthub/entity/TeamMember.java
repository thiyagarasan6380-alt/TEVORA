package com.thiyagarasan.studenthub.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "team_members", uniqueConstraints = {
        @UniqueConstraint(name = "uq_team_student", columnNames = { "team_id", "student_id" })
})
public class TeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(name = "joined_at", updatable = false)
    private LocalDateTime joinedAt;

    public TeamMember() {
    }

    @PrePersist
    protected void onCreate() {
        joinedAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }
}