package shiro.command;

import shiro.exception.ShiroFileLoadingErrorException;
import shiro.exception.ShiroEmptyDateException;
import shiro.exception.ShiroEmptyDescriptionException;
import shiro.message.Message;
import shiro.storage.Storage;
import shiro.task.Task;
import shiro.task.TaskList;

/**
 * represents a command to add a deadline task
 */
public class DeadlineCommand extends AddTaskCommand {

    /**
     * class constructor
     * @param fullCommand the full command given by the user
     */
    public DeadlineCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * creates a new deadline task which is added to the task list.
     * this change is reflected in the storage.
     * finally, the method returns the command result indicating that the task was successfully added to the task list
     * @param taskList the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return the command result indicating that the task list was successfully cleared
     * @throws ShiroEmptyDescriptionException if the description for the deadline task is empty
     * @throws ShiroEmptyDateException if the date for the deadline task is empty
     * @throws ShiroFileLoadingErrorException if there was an error loading the file
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) throws ShiroEmptyDescriptionException, ShiroEmptyDateException, ShiroFileLoadingErrorException {
        Task addedTask = taskList.addDeadline(fullCommand);
        storage.save(taskList.getTasks());
        return new CommandResult(Message.addedTaskMessage(addedTask, taskList));
    }
}
