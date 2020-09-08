package duke.task;

/**
 * Represents a Task. A <code>Task</code> object contains a description, keeps track of
 * whether it has been completed and stores a type.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected Type type;

    /**
     * Task types available.
     */
    public enum Type {
        TODO, DEADLINE, EVENT
    }

    protected Task(String desc) {
        this.description = desc;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return this.getTypeIcon() + this.getStatusIcon() + " " + description;
    }

    protected String getStatusIcon() {
        String icon = (isDone ? "\u2713" : "\u2718"); // 2713 tick, 2718 cross
        return "[" + icon + "]";
    }

    protected abstract String getTypeIcon();

    public void markDone() {
        isDone = true;
    }
}
