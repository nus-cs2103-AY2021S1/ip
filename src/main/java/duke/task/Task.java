package duke.task;

public class Task {
    protected final String description;
    protected boolean isDone;
    public final TaskType taskType;

    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public String getStatusIcon() {
        return (isDone ? "✓" : "✘");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public TaskType getTaskType() {
        return this.taskType;
    }

    @Override
    public String toString() {
        return taskType + "[" + getStatusIcon() +"] " + description;
    }
}

//deadline task1 /by 21/8/2020 1900
//deadline task3 /by 24/8/2020 2000
//event task2 /at 21/8/2020 1800