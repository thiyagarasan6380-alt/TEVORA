
package com.thiyagarasan.studenthub.dto;

import java.util.List;

public class SkillAssignmentRequest {

    private List<Integer> skillIds;

    public SkillAssignmentRequest() {
    }

    public List<Integer> getSkillIds() {
        return skillIds;
    }

    public void setSkillIds(List<Integer> skillIds) {
        this.skillIds = skillIds;
    }
}
