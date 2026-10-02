package model;

import java.util.ArrayList;

public class Team {
    private String teamName;
    private ArrayList<Student> members = new ArrayList<>();

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void addMember(Student student) {
        members.add(student);
    }

    public ArrayList<Student> getMembers() {
        return members;
    }
}