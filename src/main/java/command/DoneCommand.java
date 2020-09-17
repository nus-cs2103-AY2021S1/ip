package command;

import exception.InvalidInputException;
import exception.InvalidSaveFileException;
import logic.Storage;
import logic.Ui;
import tasks.Task;
import tasks.TaskList;

/**
 * Represents the command from the user to complete an
 * existing task.
 */
public class DoneCommand extends Command {

    public DoneCommand(String input) {
        super(input);
    }

    /**
     * Completes the task that the user specified.
     * @param tasks List of tasks.
     * @param ui logic.Ui Object that handles printing.
     * @param storage Handles the rewriting of save file.
     * @throws InvalidInputException If command is poorly written.
     * @throws InvalidSaveFileException If there is an issue writing the save file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage)
            throws InvalidInputException, InvalidSaveFileException {

        final int INPUT_INDEX = 5;
        //Check if task was specified
        if (super.input.length() <= INPUT_INDEX) {
            throw new InvalidInputException("☹ OOPS!!! Please specify which task you want to complete!");
        }

        try {
            int completed = Integer.parseInt(super.input.substring(INPUT_INDEX));
            Task current = tasks.getTasks().get(completed - 1);
            current.completeTask();
            storage.saveFile(tasks.getTasks());
            return ui.printOutput("Nice! I've marked this task as done:\n" + current.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidInputException("Index out of bounds! Please try again.");
        } catch (NumberFormatException err) {
            throw new InvalidInputException("Please enter a number!");
        }
    }
}
