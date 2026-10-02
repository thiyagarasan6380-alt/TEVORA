package model;

public class Student extends User {
    private String skill;

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getSkill() {
        return skill;
    }

    public void displayRole() {
        System.out.println("Student");
    }
}