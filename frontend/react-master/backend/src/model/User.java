package model;

public abstract class User {
    private String name;

    public void displayRole() {
        System.out.println("User");
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
