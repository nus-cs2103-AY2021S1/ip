package duke.task;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected boolean isDone;
    String dateString;
    LocalDate date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.dateString = "0001-01-01";
        this.date = LocalDate.parse("0001-01-01");
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public LocalDate getDate() {
        return date;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String toFileString() {
        return "Error";
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
