package duke.task;

public class Task {
    public static final String ICON_TICK = "✓";
    public static final String ICON_CROSS = "✗";

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() { return this.description; };

    public void setDone(boolean status) {
        this.isDone = status;
    }

    public String getStatusIcon() {
        return (isDone ? ICON_TICK : ICON_CROSS); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
