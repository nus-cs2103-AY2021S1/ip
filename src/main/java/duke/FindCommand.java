package duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a find command for a task.
 */
public class FindCommand extends Command {

    /** User input as a string */
    private final String userInput;

    /**
     * Constructs a FindCommand object with user input.
     * @param userInput User input as a string.
     */
    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes finding tasks with keyword.
     * @param taskList Task list containing tasks.
     * @param storage Storage for storing and retrieving all tasks.
     * @param ui Handles printing of user interaction.
     * @throws DukeException When invalid inputs are entered.
     * @throws IOException When invalid inputs are entered.
     */
    @Override
    public void execute(
            TaskList taskList, Storage storage, Ui ui) throws DukeException, IOException {
        ui.printFindings(taskList.findTasks(userInput));
    }
}
