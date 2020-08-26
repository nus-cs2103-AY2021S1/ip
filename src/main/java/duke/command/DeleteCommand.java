package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.Ui;
import main.java.duke.exceptions.InvalidFileException;
import main.java.duke.exceptions.InvalidInputException;
import main.java.duke.tasks.Task;
import main.java.duke.tasks.TaskList;

/**
 * Delete Command class to execute command that deletes a task in
 * the TaskList.
 */
public class DeleteCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Noted. I've removed this task:\n";

    public DeleteCommand(String input) {
        super(input);
    }

    /**
     * Execute a Delete Command to delete a task in the task list.
     * @param tasks TaskList of tasks.
     * @param ui Ui object from the Ui class.
     * @param storage Storage object from the Storage class.
     * @throws InvalidInputException incorrect input after delete command.
     * @throws InvalidFileException failed to save file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws InvalidInputException, InvalidFileException {
        if (super.input.length() <= 7) {
            throw new InvalidInputException
                    ("☹ OOPS!!! The description of a delete operation cannot be empty / invalid index.");
        }
        int index = Integer.parseInt(super.input.substring(7)) - 1;
        Task taskDeleted = tasks.getTask(index);
        tasks.removeTask(index);
        ui.printMessage(MESSAGE_SUCCESS + taskDeleted);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
