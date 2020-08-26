package main.java.duke.command;

import main.java.duke.Storage;
import main.java.duke.Ui;
import main.java.duke.exceptions.InvalidFileException;
import main.java.duke.exceptions.InvalidInputException;
import main.java.duke.tasks.TaskList;

/**
 * ExitCommand class to execute command to quit and exit Duke.
 */
public class ExitCommand extends Command {

    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting...";

    public ExitCommand(String input) {
        super(input);
    }

    /**
     * Execute the exit process.
     * @param tasks TaskList of tasks.
     * @param ui Ui object from the Ui class.
     * @param storage Storage object from the Storage class.
     * @throws InvalidFileException failed to save file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
            throws InvalidInputException, InvalidFileException {
        ui.showExitMessage();
        storage.save(tasks);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}