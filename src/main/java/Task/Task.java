package task;

import java.time.LocalDateTime;

public abstract class Task {
    protected int position;
    protected String taskDescription;
    protected boolean taskCompleted;
    protected LocalDateTime date;

    public Task(String taskDescription) {
        this.taskDescription = taskDescription;
        taskCompleted = false;
    }

    public abstract String saveFormat();

    public void setPosition(int position) {
        this.position = position;
    }

    public void completeTask() {
        taskCompleted = true;
    }

    public String getTaskDescription() {
        return taskDescription;
    }
}
