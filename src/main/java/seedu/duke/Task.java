package seedu.duke;

import java.time.LocalDateTime;

public class Task {
    protected String name;
    protected boolean isDone;

    public Task(String name, boolean status) {
        this.name = name;
        this.isDone = status;
    }

    public String getStatus() {
        return this.isDone? ("[\u2713] " + toString()) : ("[\u2718] " + toString());
    }

    public void checkTask() {
        this.isDone = !this.isDone;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getType() {
        return "";
    }

    public LocalDateTime getTime() {
        return null;
    }

    public boolean isComplete() {
        return this.isDone;
    }

    /**
     * Returns the name of the task.
     * @return String representing the name of the task.
     */
    public String getName() {
        return this.name;
    }
}
