
package com.thiyagarasan.studenthub.dto;

public class SkillResponse {

    private int id;
    private String skillName;

    public SkillResponse(int id, String skillName) {
        this.id = id;
        this.skillName = skillName;
    }

    public int getId() {
        return id;
    }

    public String getSkillName() {
        return skillName;
    }
}
