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
     */
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        if (!userInput.substring(6).isBlank()) {
            try {
                String toDelete = userInput.substring(7);
                int index = Integer.parseInt(toDelete);
                if (index <= taskList.size() && index > 0) {
                    System.out.println(Ui.line);
                    System.out.println(Ui.bot);
                    System.out.println("Noted! I've deleted this task:");
                    System.out.println(taskList.getTasks().get(index - 1));
                    taskList.getTasks().remove(index - 1);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    storage.writeToFile(taskList.getTasks());
                } else {
                    throw new IndexOutOfBoundsException();
                }
            } catch (NumberFormatException | IndexOutOfBoundsException | IOException ex) {
                throw new DukeException("The number keyed in is invalid!");
            }
        } else {
            throw new DukeException("The description of a delete cannot be empty!");
        }
    }
}
