package duke.task;

import duke.command.InvalidCommandException;

import java.time.LocalDate;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public void markAsDone() throws InvalidCommandException {
        if (isDone) {
            throw new InvalidCommandException("The task " + this + " has already been done.");
        }
        isDone = true;
    }

    public String output() {
        int done = isDone ? 1 : 0;
        return " | " + done + " | " + description;
    }

    public boolean happenOnDate(LocalDate date) {
        return false;
    }

    public boolean happenToday() {
        return false;
    }

    public boolean happenBeforeDate(LocalDate date) {
        return false;
    }

    public boolean happenBeforeToday() {
        return false;
    }

    public boolean happenAfterDate(LocalDate date) {
        return false;
    }

    public boolean happenAfterToday() {
        return false;
    }

    public boolean happenBetween(LocalDate date1, LocalDate date2) {
        return false;
    }

    public boolean happenIn(int n) {
        return false;
    }

    /**
     * Checks whether the task description contains a designated substring
     * @param s the substring to find
     * @return true if s is found in the task description
     */
    public boolean finds(String s) {
        return description.contains(s);
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
