package duke;

import java.io.IOException;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command {

    /** User input as a String */
    private final String userInput;

    /**
     * Constructs a DeleteCommand object with user input.
     *
     * @param userInput User input as a String.
     */
    DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the deleting of a task.
     * @param taskList Task list containing tasks.
     * @param storage Storage for storing and retrieving all tasks.
     * @param ui Handles printing of user interaction.
     * @return Text when DeleteCommand is executed.
     * @throws DukeException When input for delete is invalid, respective error messages are printed.
     * @throws IOException When input for delete is invalid, respective error messages are printed.
     */
    @Override
    public String execute(
            TaskList taskList, Storage storage, Ui ui) throws DukeException, IOException {
        assert taskList != null : "Task list cannot be null";
        assert storage != null : "Storage cannot be null";
        assert ui != null : "Ui cannot be null";
        storage.writeToFile(taskList.getTasks());
        return ui.displayDelete(userInput, taskList);
    }
}
