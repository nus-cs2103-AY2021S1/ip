package duke.command;

import duke.Output;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.exception.EmptyDescriptionException;
import duke.task.Task;

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
        this.isExit = false;
    }

    /**
     * searches for a list of tasks that matches the query string.
     * finally, returns a string representation of the list of tasks matching the query string
     * @param tasks the list of tasks
     * @param storage the storage system responsible for saving and loading data
     * @return the string representation of the list of tasks matching the query string
     * @throws EmptyDescriptionException if the query string given by the user is empty
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws EmptyDescriptionException {
        if (fullCommand.length() < 6) {
            throw new EmptyDescriptionException("oh dear :-( the description of 'find' cannot be empty");
        }
        ArrayList<Task> matchingTasks = tasks.getMatchingTasks(fullCommand.substring(5));
        return Output.matchingTasksMessage(matchingTasks);
    }
}
