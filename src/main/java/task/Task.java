package task;

import java.util.Optional;
import java.util.Date;

public abstract class Task {
    protected final String description;
    protected boolean completed;

    protected static String TICK = "\u2713";
    protected static String CROSS = "\u2717";

    Task(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    public String toStringSuffix() {
        String symbol = this.completed ? TICK : CROSS;
        return String.format("[%s] %s", symbol, this.description);
    }

    public void completeTask() {
        this.completed = true;
    }

    public String getTaskDescription() {
        return this.description;
    }

    public boolean isTaskCompleted() {
        return this.completed;
    }

    public abstract String getTaskSymbol();

    public abstract Optional<String> getTaskDatetime();
}
