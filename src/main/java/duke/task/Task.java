package duke.task;

public abstract class Task {
    private final String name;
    private boolean isDone;

    /**
     * A base Task to inherit from.
     * @param name name of Task
     * @param isDone whether Task is done
     */
    public Task(String name, boolean isDone) {
        assert name != null : "Task name is null";
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Marks task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Returns whether Task is done.
     * @return boolean of task is done
     */
    public boolean getIsDone() {
        return isDone;
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
        String doneSymbol = getIsDone() ? "✓" : "✗";
        return String.format("[%s] %s", doneSymbol, getName());
    }

    /**
     * Compares two tasks and check if they are the same
     * @param o object to compare equality
     * @return true if the tasks are the same
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Task)) {
            return false;
        }
        Task t = (Task) o;
        return name.equals(t.name);
    }

    /**
     * Returns String representation of Task to be saved.
     * @return String representation of Task to be saved
     */
    public abstract String toSaveString();
}
