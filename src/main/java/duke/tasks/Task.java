package duke.tasks;

/**
 * Represents a task with a description
 * that can be checked as done/undone.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task.
     * @param description Task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Gets {@code isDone} status of the task.
     * @return isDone - tick or X symbols
     */
    public String getStatusIcon() {
        return (isDone ? "/" : "X"); //return tick or X symbols
    }

    public void setDone() {
        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    public abstract String[] getSaveData();

    /*public abstract void postpone(int amount, ChronoUnit timeUnit);

    public abstract void advance(int amount, ChronoUnit timeUnit);*/
}

