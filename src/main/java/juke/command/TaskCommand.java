package juke.command;

import juke.Storage;
import juke.TaskList;
import juke.task.Task;

/**
 * Represents the command to add a task to current list of tasks.
 */
public class TaskCommand extends Command {

    private Task task;

    /**
     * Constructs a TaskCommand with a given Task.
     * @param task
     */
    public TaskCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Executes the addition of a task to current list of Tasks.
     * @param taskList List of tasks
     * @param storage Storage of tasks onto disk
     * @return Output text upon addition of task.
     */
    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        return taskList.addToList(this.task);
    }
}
