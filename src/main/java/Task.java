/**
 * Represents a Task. A <code>Task</code> object contains a description, keeps track of
 * whether it has been completed and stores a type.
 */
public class Task {
    protected String description;
    protected boolean isDone;
    protected Type type;

    /**
     * Task types available.
     */
    public enum Type {
        TODO, DEADLINE, EVENT, NONE
    }

    protected Task(String desc) {
        this.description = desc;
        this.isDone = false;
        this.type = Type.NONE;
    }

    @Override
    public String toString() {
        return this.getTypeIcon() + this.getStatusIcon() + " " + description;
    }

    protected String getStatusIcon() {
        String icon = (isDone ? "\u2713" : "\u2718");
        return "[" + icon + "]";
    }

    protected String getTypeIcon() {
        String icon;
        switch (type) {
        case TODO:
            icon = "T";
            break;
        case DEADLINE:
            icon = "D";
            break;
        case EVENT:
            icon = "E";
            break;
        case NONE:
            icon = "?";
            break;
        default:
            icon = "!";
        }
        return "[" + icon + "]";
    }

    public void markDone() {
        isDone = true;
    }
}