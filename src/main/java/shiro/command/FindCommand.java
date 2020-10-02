package shiro.command;

import shiro.message.Message;
import shiro.storage.Storage;
import shiro.task.TaskList;
import shiro.exception.ShiroEmptyDescriptionException;
import shiro.task.Task;

import java.util.ArrayList;

/**
 * represents a command to find a list of commands that contain the query string
 */
public class FindCommand extends Command {

    /**
     * class constructor
     * @param fullCommand the full command given by the user
     */
    public FindCommand(String fullCommand) {
        super(fullCommand);
    }

    /**
     * searches for a list of tasks that matches the query string.
     * finally, returns a command result containing the list of tasks matching the query string
     * @param taskList the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return a command result containing the list of tasks matching the query string
     * @throws ShiroEmptyDescriptionException if the query string given by the user is empty
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) throws ShiroEmptyDescriptionException {
        if (fullCommand.length() < 6) {
            throw new ShiroEmptyDescriptionException("oh dear :-( the description of 'find' cannot be empty");
        }
        ArrayList<Task> matchingTasks = taskList.getMatchingTasks(fullCommand.substring(5));
        return new CommandResult(Message.matchingTasksMessage(matchingTasks));
    }
}
