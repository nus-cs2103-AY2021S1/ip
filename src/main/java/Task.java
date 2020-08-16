package main.java;
import java.util.*;

public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return isDone
                ? "\u2713"
                : "\u2718";
    }

    public String getDescription() { return this.description; }

    public void finishTask() { isDone = true; }

}
