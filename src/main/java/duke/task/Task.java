package duke.task;

import duke.Encodable;

public abstract class Task implements Encodable<Task> {

    protected String description;
    protected boolean completed;

    protected Task(String description) {
        this.description = description;
        completed = false;
    }

    public void setCompleted() {
        completed = true;
    }

    private String getStatusIcon() {
        return completed ? "\u2713" : "\u2718";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
