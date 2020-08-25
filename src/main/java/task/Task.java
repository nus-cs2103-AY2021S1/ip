package task;

import java.time.LocalDate;

public abstract class Task {
    protected String description;
    protected boolean completed;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.completed = isDone;
    }

    public void setCompleted() {
        this.completed = true;
    }

    public abstract String toEncoding();
    public abstract boolean isDate(LocalDate dateFilter);
}
