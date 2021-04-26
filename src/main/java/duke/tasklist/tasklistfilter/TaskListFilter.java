package duke.tasklist.tasklistfilter;

import duke.task.Task;

/**
 * Interface for the filter operations inside TaskList.
 */
public interface TaskListFilter {
    boolean filter(Task task);
}
