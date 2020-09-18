package willy.task;

/**
 * Work that people need to do will be keep tracked of by the bot.
 * Status of task to be marked done or undone.
 */
public class Task {
    private boolean isDone;
    private String task;
    private TaskSymbol taskType;

    public Task(String task, TaskSymbol taskType) {
        this.task = task;
        this.taskType = taskType;
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getTask() {
        return task;
    }

    public TaskSymbol getTaskType() {
        return taskType;
    }

    public void setTaskDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    public String getStatusInStore() {
        return (isDone ? "O" : "X");
    }

    @Override
    public String toString() {
        return taskType + "[" + getStatusIcon() + "]" + " " + task;

    }
}
