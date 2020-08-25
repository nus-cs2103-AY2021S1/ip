package command;

import task.Task;
import task.TaskList;

public class addCommand extends Command {
    Task task;

    public addCommand(TaskList tasks, Task task) {
        super(tasks);
        this.task = task;
    }

    /**
     * Adds task to list of tasks.
     */
    @Override
    public void execute() {
        this.tasks.addTask(this.task);
    }
}
