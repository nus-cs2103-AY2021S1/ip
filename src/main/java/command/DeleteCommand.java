package command;

import exception.InvalidInputException;
import exception.InvalidSaveFileException;
import logic.Storage;
import logic.Ui;
import tasks.Task;
import tasks.TaskList;

/**
 * Represents the command from the user when they want to
 * delete a command.
 */
public class DeleteCommand extends Command {
    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * Executes the delete on the task that the user specified.
     *
     * @param tasks List of tasks given.
     * @param ui Handles the output to print.
     * @param storage Writes the save file.
     * @throws InvalidInputException If the input for the delete is incorrect.
     * @throws InvalidSaveFileException If there is an issue writing the save file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws InvalidInputException, InvalidSaveFileException {

        final int INPUT_INDEX = 7;
        //Check if description is empty
        if (super.input.length() <= INPUT_INDEX) {
            throw new InvalidInputException(
                    "\t☹ OOPS!!! The description of a delete operation cannot be empty / invalid index.");
        }

        int index;
        try {
            index = Integer.parseInt(super.input.substring(INPUT_INDEX));
        } catch (NumberFormatException e) {
            throw new InvalidInputException("What you entered is not a number!");
        }
        if (index > tasks.getTasks().size() || index <= 0) {
            throw new InvalidInputException(
                    "\t☹ OOPS!!! The description of a delete operation cannot be empty / invalid index.");
        }
        Task task = tasks.getTasks().get(index - 1);
        tasks.removeTask(index - 1);
        storage.saveFile(tasks.getTasks());
        return ui.printOutput("\tNoted. I've removed this task:\n" + "\t" + task.toString()
                + "\n\tNow you have " + tasks.getTasks().size() + " tasks in the list.");
    }
}
