package duke.task;

public abstract class Task {
    private final String name;
    private boolean done;

    /**
     * A base Task to inherit from.
     * @param name name of Task
     * @param done whether Task is done
     */
    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    /**
     * Mark task as done.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Returns whether Task is done.
     * @return boolean of task is done
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Gets name of task.
     * @return name of task as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Returns String representation of Task presented to user.
     * @return String representation of Task presented to user
     */
    @Override
    public String toString() {
        String doneSymbol = isDone() ? "✓" : "✗";
        return String.format("[%s] %s", doneSymbol, getName());
    }

    /**
     * Returns String representation of Task to be saved.
     * @return String representation of Task to be saved
     */
    public abstract String toSaveString();
}
