public class Task {
    protected String description;
    protected boolean isDone;
    protected Type type;

    public enum Type { TODO, DEADLINE, EVENT, UNKNOWN }

    protected Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = Type.UNKNOWN;
    }

    @Override
    public String toString() {
        return this.getTypeIcon() + this.getStatusIcon() + " " + this.description;
    }

    protected String getStatusIcon() {
        String icon = (isDone ? "\u2713" : "\u2718");
        return "[" + icon + "]";
    }

    protected String getTypeIcon() {
        String icon;
        switch (this.type) {
        case TODO:
            icon = "T";
            break;
        case DEADLINE:
            icon = "D";
            break;
        case EVENT:
            icon = "E";
            break;
        case UNKNOWN:
            icon = "?";
            break;
        default:
            icon = "Error";
        }
        return "[" + icon + "]";
    }

    public void markDone() {
        this.isDone = true;
    }
}
