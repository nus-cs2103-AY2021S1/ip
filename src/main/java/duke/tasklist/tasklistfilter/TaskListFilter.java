package duke.tasklist.tasklistfilter;

import duke.task.Task;

public interface TaskListFilter {
    public boolean filter(Task task);
}
