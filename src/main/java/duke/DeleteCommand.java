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
        String deletedTask;
        if (!userInput.substring(6).isBlank()) {
            try {
                String toDelete = userInput.substring(7);
                int deletedTaskIndex = Integer.parseInt(toDelete);
                if (deletedTaskIndex <= taskList.size() && deletedTaskIndex > 0) {
                    deletedTask = taskList.getTasks().get(deletedTaskIndex - 1).toString();
                    taskList.getTasks().remove(deletedTaskIndex - 1);
                } else {
                    throw new DukeException("The number keyed in is invalid!");
                }
            } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                throw new DukeException("The number keyed in is invalid!");
            }
        } else {
            throw new DukeException("The description of a delete cannot be empty!");
        }
        storage.writeToFile(taskList.getTasks());
        return ui.displayDelete(deletedTask, taskList);
    }
}
