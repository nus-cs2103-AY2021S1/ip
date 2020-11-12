package duke.task;

import duke.tag.TagList;

/**
 * <p>The duke.task.ToDoTask class defines the behavior of a todo task that only
 * has description and status.</p>
 */
public class ToDoTask extends Task {
    public ToDoTask(String taskName, boolean isDone, TagList tagList) {
        super(taskName, isDone, tagList);
    }

    @Override
    public String serialiseTask() {
        int isDone = getTaskStatus() ? 1 : 0;
        return "todo %% " + getTaskDescription() + " %% " + isDone + " %% " + getTagList();
    }

    @Override
    public String toString() {
        return "[T] [" + getStatusIcon() + "] " + taskDescription;
    }
}
