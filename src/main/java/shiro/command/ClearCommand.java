package shiro.command;

import shiro.exception.ShiroFileLoadingErrorException;
import shiro.message.Message;
import shiro.storage.Storage;
import shiro.task.TaskList;

/**
 * represents a command to clear the entire list of tasks
 */
public class ClearCommand extends Command {

    /**
     * class constructor
     */
    public ClearCommand() {
        super("clear");
    }

    /**
     * deletes all of the tasks in the given task list
     * updates this change to the storage
     * finally, the method returns the command result indicating that the task list was successfully cleared
     * @param taskList the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return the command result indicating that the task list was successfully cleared
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) throws ShiroFileLoadingErrorException {
        taskList.deleteAll();
        storage.save(taskList.getTasks());
        return new CommandResult(Message.clearedAllTasksMessage());
    }
}
