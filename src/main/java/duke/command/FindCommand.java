package duke.command;

import duke.DukeException;
import duke.History;
import duke.Storage;
import duke.task.TaskList;

/**
 * Command given to Duke to search for Tasks within Duke's TaskList.
 */
public class FindCommand extends Command {
    private final String userInput;

    /**
     * Creates a Find Command to search the TaskList for the Task with the specified key word.
     *
     * @param fullCommand <code>String</code> of the entire command from the user input.
     */
    public FindCommand(String fullCommand) {
        super();
        this.userInput = fullCommand;
    }

    /**
     * Searches for Tasks that has the keyword inputted by the user.
     *
     * @param taskList the List of all the Tasks that Duke has.
     * @param storage the database of Tasks that is saved to the user's local storage.
     * @param history the state of all changes made to Duke's TaskList.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList taskList, Storage storage, History history) throws DukeException {
        assert taskList != null : "taskList cannot be null";
        if (userInput.length() <= 4) {
            throw new DukeException("Please enter a keyword to find your task");
        }

        String keyword = this.userInput.substring(5);

        return "I've found these tasks that matches your keyword:\n" + taskList.showSpecifiedItems(keyword);
    }
}
