package shiro.command;

import shiro.exception.ShiroFileLoadingErrorException;
import shiro.exception.ShiroEmptyDateException;
import shiro.exception.ShiroEmptyDescriptionException;
import shiro.message.Message;
import shiro.storage.Storage;
import shiro.task.Task;
import shiro.task.TaskList;

/**
 * represents a command to add an event task
 */
public class EventCommand extends AddTaskCommand {

    /**
     * class constructor
     * @param fullCommand the full command given by the user
     */
    public EventCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * creates a new deadline task which is added to the task list.
     * this change is reflected in the storage.
     * finally, the method returns the command result indicating that the event task was successfully added
     * @param taskList the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return the command result indicating that the event task was successfully added
     * @throws ShiroEmptyDescriptionException if the description for the deadline task is empty
     * @throws ShiroEmptyDateException if the date for the deadline task is empty
     * @throws ShiroFileLoadingErrorException if there was an error loading the file
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) throws ShiroEmptyDescriptionException, ShiroEmptyDateException, ShiroFileLoadingErrorException {
        Task addedTask = taskList.addEvent(fullCommand);
        storage.save(taskList.getTasks());
        return new CommandResult(Message.addedTaskMessage(addedTask, taskList));
    }
}
