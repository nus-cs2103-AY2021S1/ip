package command;

import task.Task;
import task.TaskList;

public class AddCommand extends Command {
    protected Task task;

    /**
     * Creates AddCommand object to execute adding of given task to TaskList.
     * @param tasks TaskLists with Tasks.
     * @param task Task to be added to tasks.
     */
    public AddCommand(TaskList tasks, Task task) {
        super(tasks);
        this.task = task;
    }

    /**
     * Adds task to list of tasks.
     * @return String to inform user task has been added.
     */
    @Override
    public String execute() {
        return this.tasks.addTask(this.task);
    }
}

