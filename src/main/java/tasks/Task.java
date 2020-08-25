package tasks;

import enums.TaskEnum;

public class Task {

    private String title;

    private boolean isDone;

    private TaskEnum type;

    public Task(String title, TaskEnum type) {
        this.title = title;
        this.type = type;
        this.isDone = false;
    }

    public Task(String title, boolean isDone, TaskEnum type) {
        this.title = title;
        this.isDone = isDone;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void markDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718");
    }

    public String getSaveFormat() {
        return String.format("%s | %s | %s", this.type.getTaskLetter(), this.isDone ? 1 : 0, this.title);
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", this.type.getTaskLetter(), this.getStatusIcon(), this.title);
    }

    public boolean getIsDone() {
        return this.isDone;
    }
}
