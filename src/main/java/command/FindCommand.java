package command;

import java.util.ArrayList;

import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import task.Task;

/**
 * A FindCommand object searches for tasks within the list based on keyword.
 *
 * @author amelia
 * @version 1.0
 * @since 2020-09-01
 */
public class FindCommand extends Command {
    /**
     * Displays tasks containing keyword.
     *
     * @param inputMsg User's input message to the chat bot.
     * @param currList Current list of tasks.
     * @param ui Ui object relevant to the chat bot.
     * @return String message representing tasks with matching keywords.
     * @throws DukeException If user does not specify a keyword or there were no matches.
     */
    @Override
    public String execute(String inputMsg, TaskList currList, Ui ui) throws DukeException {
        int numOfWords = inputMsg.split(" ").length;
        if (numOfWords <= 1) {
            throw new DukeException("Enter keyword to search for!");
        } else {
            ArrayList<Task> searchResult = currList.searchFor(inputMsg.substring(5));
            if (searchResult.isEmpty()) {
                throw new DukeException("Unable to find keyword.");
            } else {
                return ui.findTask(searchResult);
            }
        }
    }
}
