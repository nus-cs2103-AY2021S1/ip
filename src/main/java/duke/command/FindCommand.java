package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the Command to find tasks with keyword.
 */
public class FindCommand implements Command {
    private String keyword;
    /**
     * Return new Command that will find all tasks that contains the keyword.
     * @param keyword The keyword to search in the TaskList.
     * @throws DukeException when there is error creating the command
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Execute the command.
     *
     * @param storage             Storage to save data to.
     * @param tasks               The tasklist to save the data to.
     * @param terminationFunction Function to run if this is the bye command.
     * @return The response of Duke to the user Input.
     * @throws DukeException if the system fails to execute.
     */
    @Override
    public String execute(Storage storage, TaskList tasks, Runnable terminationFunction) throws DukeException {
        String result = tasks.find(keyword);
        if (result.equals("")) {
            return Ui.show("No match found");
        }
        return Ui.show("These following tasks match the keyword you entered: \n" + result);
    }
}
