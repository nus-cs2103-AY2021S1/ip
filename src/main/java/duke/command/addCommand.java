package duke.command;

import duke.task.Task;
import duke.task.TaskList;

public class addCommand extends Command {
    Task task;

    public addCommand(TaskList tasks, Task task) {
        super(tasks);
        this.task = task;
    }

    @Override
    public void execute() {
        this.tasks.addTask(this.task);
    }
}
