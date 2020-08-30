package main.java.Duke.Commands;

import main.java.Duke.Task.Task;
import main.java.Duke.Task.TaskList;

public class addCommand extends Command {
    Task task;
    public addCommand(TaskList tasklist, Task task) {
        super(tasklist);
        this.task = task;
    }

    @Override
    public void execute() {
        this.tasklist.addTask(this.task);
    }
}
