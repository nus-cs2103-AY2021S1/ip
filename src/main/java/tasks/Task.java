package tasks;

import exceptions.DataException;

public abstract class Task {

    // task description
    private final String desc;

    // indicates whether the task is done or not
    private boolean done;

    public Task(String desc) throws DataException {
        if (desc.isBlank()) {
            throw new DataException("Task Description", "Cannot be blank");
        }
        this.desc = desc;
        this.done = false;
    }

    public String getDescription() {
        return desc;
    }

    public boolean getDoneStatus() {
        return done;
    }

    protected abstract char getTaskType();

    public void markAsDone() {
        this.done = true;
    }

    private char getStatusIcon() {
        // returns tick or X symbols
        return done ? '\u2713' : '\u2718';
    }

    public abstract String getParentCommand();

    @Override
    public String toString() {
        return String.format("[%c][%c] %s", getTaskType(), getStatusIcon(), getDescription());
    }

}
