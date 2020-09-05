package duke.command;

import duke.Output;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.task.Task;

import java.util.ArrayList;

/**
 * represents a command to display the entire list of tasks
 */
public class ListCommand extends Command {

    /**
     * class constructor
     */
    public ListCommand() {
        super("list");
        this.isExit = false;
    }

    /**
     * returns a string representation of the full list of tasks
     * @param tasks the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return a string representation of the full list of tasks
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        ArrayList<Task> taskList = tasks.getTaskList();
        return Output.listAllTasksMessage(taskList);
    }
}
