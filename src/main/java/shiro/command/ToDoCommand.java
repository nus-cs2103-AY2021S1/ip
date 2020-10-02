package shiro.command;

import shiro.exception.ShiroFileLoadingErrorException;
import shiro.exception.ShiroEmptyDescriptionException;
import shiro.message.Message;
import shiro.storage.Storage;
import shiro.task.Task;
import shiro.task.TaskList;

/**
 * represents a command to add a to do task
 */
public class ToDoCommand extends AddTaskCommand {

    /**
     * class constructor
     * @param fullCommand the full command given by the user
     */
    public ToDoCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * creates a new deadline task which is added to the task list.
     * this change is reflected in the storage.
     * finally, return a command result indicating that the addition of the to do task was successful
     * @param taskList the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return command result indicating that the addition of the to do task was successful
     * @throws ShiroEmptyDescriptionException if the description for the deadline task is empty
     * @throws ShiroFileLoadingErrorException if there was an error loading the file
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) throws ShiroEmptyDescriptionException, ShiroFileLoadingErrorException {
        Task addedTask = taskList.addToDo(fullCommand);
        storage.save(taskList.getTasks());
        return new CommandResult(Message.addedTaskMessage(addedTask, taskList));
    }
}
