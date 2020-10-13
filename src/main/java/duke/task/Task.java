package duke.task;

import duke.Storage;

public class Task {

    private static final String tick = "\u2713";
    private static final String cross = "\u2718";
    private String description;
    private String taskMarker = "";
    private boolean isDone = false;
    private Priority priority = Priority.UNDEFINED;

    public Task(String description) {
        this.description = description;
    }

    protected Task(String description, String taskMarker) {
        this.description = description;
        this.taskMarker = taskMarker;
    }

    protected Task(String description, Priority p, String taskMarker) {
        this.description = description;
        this.priority = p;
        this.taskMarker = taskMarker;
    }

    public void addPriority(Priority p) {
        this.priority = p;
    }

    public void markDone() {
        this.isDone = true;
    }

    @Override
    public String toString() {
        String status = isDone ? tick : cross;
        return boxFormat(taskMarker) + boxFormat(status)
                + boxFormat(priority.toString()) + " " + description;
    }

    public String getSaveFormat() {
        int isDoneInt = isDone ? 1 : 0;
        return taskMarker + Storage.LINE + isDoneInt
                + Storage.LINE + description
                + Storage.LINE + priority.toString();
    }

    protected String boxFormat(String symbol) {
        return String.format("[%s]", symbol);
    }

    public String getDescription() {
        return description;
    }
}
