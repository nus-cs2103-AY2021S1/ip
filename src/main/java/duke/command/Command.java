package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exceptions.InvalidFileException;
import duke.exceptions.InvalidInputException;
import duke.tasks.TaskList;

/**
 * Abstract Command class which executes specific commands.
 * Takes in input from the Parser.
 */
public abstract class Command {
    public final String input;

    public Command(String input) {
        this.input = input;
    }

    /**
     * Execute specific commands.
     * @param tasks TaskList of tasks.
     * @param ui Ui object from the Ui class.
     * @param storage Storage object from the Storage class.
     * @throws InvalidInputException when the input cannot be read.
     * @throws InvalidFileException when the file cannot be read or written.
     */

    public abstract void execute(TaskList tasks, Ui ui, Storage storage)
            throws InvalidInputException, InvalidFileException;

    /**
     * To indicate if command is an exit command.
     * @return true if command exits Duke and false otherwise.
     */
    public abstract boolean isExit();

}
