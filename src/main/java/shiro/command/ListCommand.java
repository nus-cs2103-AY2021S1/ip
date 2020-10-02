package shiro.command;

import shiro.message.Message;
import shiro.storage.Storage;
import shiro.task.TaskList;
import shiro.task.Task;

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
    }

    /**
     * returns a command result containing the full list of tasks
     * @param taskList the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return a a command result containing the full list of tasks
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) {
        ArrayList<Task> tasks = taskList.getTasks();
        return new CommandResult(Message.listAllTasksMessage(tasks));
    }
}
