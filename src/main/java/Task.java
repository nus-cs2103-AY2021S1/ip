package main.java;

import java.io.Serializable;

public abstract class Task implements Serializable {
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
