package duke.task;

import duke.exception.ExceptionMessage;
import duke.ui.UiPrint;

/**
 * Task is the super class of all types of tasks, cannot be instantiated.
 */
public class Task {

    public static final String NO_TAG = "no tag";

    private String icon;
    private String description;
    private String taskInfo;
    private String tag;
    private boolean isDone;

    protected Task(String icon, String description, String taskInfo) {
        this.icon = icon;
        this.description = description;
        this.taskInfo = taskInfo;
        tag = NO_TAG;
        isDone = false;
    }

    /**
     * Marks the task as done.
     */
    public void setTaskDone(boolean isDone) {
        this.isDone = isDone;
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

    public boolean containsKeyWord(String keyWord) {
        return description.contains(keyWord);
    }

    @Override
    public String toString() {
        String statusIcon = isDone ? UiPrint.TICK : UiPrint.CROSS;
        String tag = this.tag.equals(NO_TAG) ? "" : " #" + this.tag;

        return icon + statusIcon + " " + description + tag;
    }

    public String getTaskInfo() {
        return taskInfo;
    }

    public String getDescription() {
        return description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        assert !tag.isBlank() : ExceptionMessage.BLANK_TAG_MESSAGE;
        this.tag = tag;
    }
}
