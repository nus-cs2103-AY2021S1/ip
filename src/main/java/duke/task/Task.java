package duke.task;

import duke.Encodable;
import duke.Searchable;

public abstract class Task implements Encodable<Task>, Searchable {

    protected String description;
    protected boolean completed;

    protected Task(String description) {
        this.description = description;
        this.completed = false;
    }

    public void setCompleted() {
        this.completed = true;
    }

    private String getStatusIcon() {
        return this.completed ? "\u2713" : "\u2718";
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
