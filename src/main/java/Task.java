package main.java;

public class Task {
    private enum Status {
        DONE, ONGOING
    }
    private String taskName;
    private Status status;

    Task(String taskName) {
        this.taskName = taskName;
        status = Status.ONGOING;
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