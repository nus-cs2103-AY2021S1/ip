package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
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
     * @param ui the user interface object responsible for system related commands
     * @param storage the storage system responsible for saving and loading data
     * @return the string representation of the list of tasks matching the query string
     * @throws EmptyDescriptionException if the query string given by the user is empty
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws EmptyDescriptionException {
        if (fullCommand.length() < 6) {
            throw new EmptyDescriptionException("oh dear :-( the description of 'find' cannot be empty");
        }
        ArrayList<Task> matchingTasks = tasks.getMatchingTasks(fullCommand.substring(5));
        return matchingTasksMessage(matchingTasks);
    }

    private String matchingTasksMessage(ArrayList<Task> matchingTasks) {
        StringBuilder sb = new StringBuilder();

        if (matchingTasks.size() == 0) {
            sb.append("there are no tasks matching the given search");
        } else {
            sb.append("here are the matching tasks in your list:\n");

            for (int i = 0; i < matchingTasks.size(); i++) {
                sb.append(i+1).append(". ").append(matchingTasks.get(i)).append("\n");
            }
        }
        return sb.toString().trim();
    }
}
