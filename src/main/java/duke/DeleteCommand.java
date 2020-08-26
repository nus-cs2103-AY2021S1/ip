package duke;

import java.io.IOException;

/**
 * Represents a delete command.
 */
public class DeleteCommand extends Command {

    /** User input as a String */
    String userInput;

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
     *
     * @param taskList Task list containing tasks.
     * @param storage Storage for storing and retrieving all tasks.
     * @param ui Handles printing of user interaction.
     * @throws DukeException When input for delete is invalid, respective error messages are printed.
     * @throws IOException When input for delete is invalid, respective error messages are printed.
     */
    @Override
    public void execute(
            TaskList taskList, Storage storage, Ui ui) throws DukeException, IOException {
        ui.printDelete(userInput, taskList);
        storage.writeToFile(taskList.getTasks());
    }
}
