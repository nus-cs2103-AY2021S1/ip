package command;

import duke.TaskList;
import duke.Ui;
import exception.DukeException;
import task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {
    /**
     * Displays tasks containing keyword.
     *
     * @param inputMsg User's input message to the chatbot.
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
