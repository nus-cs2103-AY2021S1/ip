package duke.task;

import java.time.LocalDate;

public class Task {
    protected String description;
    protected String identity;
    protected boolean isDone;
    protected LocalDate date;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, LocalDate date) {
        this.description = description;
        this.date = date;
    }

    public Task(String description, LocalDate date, String identity) {
        this.description = description;
        this.date = date;
        this.identity = identity;
    }

    /**
     * Function to identify the different tasks in duke.txt
     * @return String of identity
     */
    public String getIdentity() {
        return identity;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public LocalDate getLocalDate() {
        assert !(this instanceof Todo) : "Todos have no dates!";
        return date;
    }

    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}