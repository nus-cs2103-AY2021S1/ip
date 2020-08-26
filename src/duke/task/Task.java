package duke.task;

import duke.ui.UIPrint;

public class Task {

    private String icon;
    private String description;
    private boolean isDone;
    public String taskInfo;

    protected Task(String icon, String description, String taskInfo) {
        this.icon = icon;
        this.description = description;
        this.taskInfo = taskInfo;
        isDone = false;
    }

    public void markAsDone() {
        isDone = true;
    }

    public String getTaskType() {
        return "task";
    }

    public boolean isTaskDone() {
        return isDone;
    }

    @Override
    public String toString() {
        String statusIcon = isDone ? UIPrint.tick : UIPrint.cross;

        return icon + statusIcon + " " + description;
    }
}
