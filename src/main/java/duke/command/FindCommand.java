package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;

import duke.task.TaskList;

/**
 * Command given to Duke to search for Tasks within Duke's TaskList.
 */
public class FindCommand extends Command{
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
     * @param ui the object responsible for user interactions.
     * @param storage the database of Tasks that is saved to the user's local storage.
     * @throws DukeException
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (userInput.length() <= 4) {
            throw new DukeException("Please enter a keyword to find your task");
        }

        String keyword = this.userInput.substring(5);
        taskList.showSpecifiedItems(keyword);
    }
}