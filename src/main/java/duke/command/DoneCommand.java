package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.Ui;
import main.java.duke.exceptions.InvalidFileException;
import main.java.duke.exceptions.InvalidInputException;
import main.java.duke.tasks.Task;
import main.java.duke.tasks.TaskList;

/**
 * DoneCommand class to execute command that mark a task in the task list
 * as done.
 */
public class DoneCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Nice! I've marked this task as done:\n";


    public DoneCommand(String input) {
        super(input);
    }

    /**
     * Execute a command to mark a task as done.
     * @param tasks TaskList of tasks.
     * @param ui Ui object from the Ui class.
     * @param storage Storage object from the Storage class.
     * @throws InvalidInputException incorrect input following the done command.
     * @throws InvalidFileException failed to save file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws InvalidInputException, InvalidFileException {
        if (super.input.length() <= 4) {
            throw new InvalidInputException
                    ("☹ OOPS!!! Please choose a task to be completed.");
        }
        int index = Integer.parseInt(super.input.substring(5)) - 1;
        Task taskDone = tasks.getTask(index);
        tasks.markAsDone(index);
        ui.printMessage(MESSAGE_SUCCESS + taskDone);
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
