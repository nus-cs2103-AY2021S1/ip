package duke.task;

import duke.exception.ExceptionMessage;
import duke.time.Time;
import duke.ui.UiPrint;

/**
 * Task is the super class of all types of tasks, cannot be instantiated.
 */
public class Task {

    public static final String NO_TAG = "Nil";

    private String icon;
    private String description;
    private Time time;
    private String taskInfo;
    private String tag;
    private boolean isDone;

    protected Task(String icon, String description, String taskInfo) {
        this.icon = icon;
        this.description = description;
        time = Time.stringToTime("Nil");
        this.taskInfo = taskInfo;
        tag = NO_TAG;
        isDone = false;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * Gets the string of the task type.
     * @return task type
     */
    public String getTaskType() {
        return "task";
    }

    public boolean isDone() {
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

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getIcon() {
        String taskType;
        if (icon.equals(UiPrint.DEADLINE_ICON)) {
            taskType = "Deadline";
        } else if (icon.equals(UiPrint.EVENT_ICON)) {
            taskType = "Event";
        } else if (icon.equals(UiPrint.TODO_ICON)) {
            taskType = "Todo";
        } else {
            return "Error!!!";
        }

        return taskType;
    }

    public String getIsDone() {
        String isDone;

        if (this.isDone) {
            isDone = "done";
        } else {
            isDone = "not done yet";
        }

        return isDone;
    }
}
