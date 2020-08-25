package main.java;

public abstract class Task {
    private String name;
    private Boolean isDone;

    public Task (String s, Boolean b) {
        name = s;
        isDone = b;
    }

    public String getName() {
        return name;
    }

    public Boolean isDone() {
        return isDone;
    }

    public void setStatus(boolean b) {
        isDone = b;
    }

}
