package duke.task;

import java.util.Optional;

public abstract class Task {
    protected final String description;
    protected boolean isCompleted;

    protected static String TICK = "\u2713";
    protected static String CROSS = "\u2717";

    Task(String description, boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }

    public String toStringSuffix() {
        String symbol = this.isCompleted ? TICK : CROSS;
        return String.format("[%s] %s", symbol, this.description);
    }

    protected boolean isEqual(Task task) {
        return this.description.equals(task.getTaskDescription()) &&
                this.isCompleted == task.isTaskCompleted();
    }

    public void completeTask() {
        this.isCompleted = true;
    }

    public String getTaskDescription() {
        return this.description;
    }

    public boolean isTaskCompleted() {
        return this.isCompleted;
    }

    public abstract String getTaskSymbol();

    public abstract Optional<String> getTaskDatetime();
}
