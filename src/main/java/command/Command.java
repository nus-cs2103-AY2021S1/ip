package command;

import exception.DukeException;
import logic.Storage;
import logic.Ui;
import tasks.TaskList;

/**
 * Abstract class of all the commands that is created
 * after parsing the user's input
 */
public abstract class Command {

    public final String input;

    /**
     * Initialises a command object.
     *
     * @param input The description of the command created.
     */
    public Command(String input) {
        assert !input.isBlank() : "Checks should have ensured a command is never blank.";
        this.input = input;
    }
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
