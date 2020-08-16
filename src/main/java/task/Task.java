package task;

public abstract class Task {
    protected final String description;
    protected boolean completed;

    protected static String TICK = "Y";
    protected static String CROSS = "N";

    Task(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    public String toStringSuffix() {
        if (this.completed) {
            return String.format("[%s] %s", TICK, this.description);
        } else {
            return String.format("[%s] %s", CROSS, this.description);
        }
    }

    public void completeTask() {
        this.completed = true;
    }
}
