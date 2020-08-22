package main.java;

public class Task {
    private enum Status {
        DONE, ONGOING
    }
    private String taskName;
    private Status status;

    Task(String taskName) {
        this.taskName = taskName;
        this.status = Status.ONGOING;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public boolean isDone() {
        return this.status == Status.DONE;
    }

    public void setStatusToDone() {
        status = Status.DONE;
    }

    @Override
    public String toString() {
        if (status == Status.DONE) {
            return "[✓] " + taskName;
        } else {
            return "[✗] " + taskName;
        }
    }
}