package juke.command;

import juke.Storage;
import juke.TaskList;
import juke.task.Task;

public class TaskCommand extends Command {

    protected Task task;

    public TaskCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        return taskList.addToList(this.task);
    }
}
