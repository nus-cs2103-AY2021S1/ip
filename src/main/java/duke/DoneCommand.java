package duke;

import java.io.IOException;

/**
 * Represents a done command for a task.
 */
public class DoneCommand extends Command {

    /** User input as a String */
    private final String userInput;

    /**
     * Constructs a new instance of a DoneCommand object with user input.
     * @param userInput User input as a String.
     */
    DoneCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the marking of task as done.
     * @param taskList Task list containing tasks.
     * @param storage Storage for storing and retrieving all tasks.
     * @param ui Handles printing of user interaction.
     * @return Text when DoneCommand is executed.
     * @throws DukeException If index < 0 or index > taskList.size().
     * @throws IOException When writing to file fails.
     */
    @Override
    public String execute(TaskList taskList,
                        Storage storage, Ui ui) throws DukeException, IOException {
        assert taskList != null : "Task list cannot be null";
        assert storage != null : "Storage cannot be null";
        assert ui != null : "Ui cannot be null";
        int length = userInput.length();
        int position = Integer.parseInt(userInput.substring(5, length));
        storage.writeToFile(taskList.getTasks());
        return ui.displayDone(taskList.getTasks(), position);
    }
}
