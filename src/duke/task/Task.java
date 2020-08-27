package duke.task;

import duke.ui.UIPrint;

/**
 * Task is the super class of all types of tasks, cannot be instantiated.
 */
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

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Gets the string of the task type.
     * @return task type
     */
    public String getTaskType() {
        return "task";
    }

    /**
     * Returns is the task done.
     * @return is the task done
     */
    public boolean isTaskDone() {
        return isDone;
    }

    public boolean containKeyWord(String keyWord) {
        return description.contains(keyWord);
    }

    @Override
    public String toString() {
        String statusIcon = isDone ? UIPrint.tick : UIPrint.cross;

        return icon + statusIcon + " " + description;
    }
}
