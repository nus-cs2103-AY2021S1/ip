package duke.task;

import java.time.format.DateTimeFormatter;

public class Task {
    private String description;
    private boolean isDone;
    public final static DateTimeFormatter INPUT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public final static DateTimeFormatter PRINT_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "] " + this.description;
    }

    public String fileText() {
        return "| " + (isDone ? "1" : "0") + " | " + this.description;
    }
}
