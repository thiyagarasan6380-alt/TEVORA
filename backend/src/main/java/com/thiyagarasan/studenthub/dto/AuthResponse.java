
package com.thiyagarasan.studenthub.dto;

public class AuthResponse {

    private String message;
    private int userId;
    private int studentId;
    private String username;
    private String name;
    private String email;

    public AuthResponse(
            String message,
            int userId,
            int studentId,
            String username,
            String name,
            String email) {

        this.message = message;
        this.userId = userId;
        this.studentId = studentId;
        this.username = username;
        this.name = name;
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public int getUserId() {
        return userId;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
