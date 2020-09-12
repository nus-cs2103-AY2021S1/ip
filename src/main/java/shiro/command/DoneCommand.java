package shiro.command;

import shiro.exception.ShiroFileLoadingErrorException;
import shiro.message.Message;
import shiro.storage.Storage;
import shiro.task.TaskList;
import shiro.exception.ShiroInvalidIndexException;
import shiro.task.Task;

/**
 * represents a command to mark the specified task as done
 */
public class DoneCommand extends Command {

    /**
     * class constructor
     * @param fullCommand the full command given by the user
     */
    public DoneCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * marks the specified task as done.
     * this change is reflected in the storage.
     * finally, the method returns the command result indicating that the task list was successfully marked as done
     * @param taskList the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return the command result indicating that the task list was successfully deleted
     * @throws ShiroInvalidIndexException if the given task number does not exist in the list
     */
    public CommandResult execute(TaskList taskList, Storage storage) throws ShiroInvalidIndexException, ShiroFileLoadingErrorException {
        int taskNumber = Integer.parseInt(fullCommand.substring(5));
        Task task = taskList.done(taskNumber);
        storage.save(taskList.getTasks());
        return new CommandResult(Message.markedTaskAsDoneMessage(task, taskList));
    }
}
