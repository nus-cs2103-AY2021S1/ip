package nite.command;

import nite.exception.NiteException;
import nite.storage.Storage;
import nite.task.TaskList;
import nite.ui.Ui;

/**
 * Represents an UnknownCommand where user input is unclear.
 */
public class InvalidCommand extends Command {

    private String input;

    /**
     * Creates an UnknownCommand.
     *
     * @param input Full input by the user.
     */
    public InvalidCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the command to inform user of unclear input.
     *
     * @param tasks Tasklist containing current tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in hard disk.
     * @return String displaying completion of Command.
     * @throws NiteException Always throws exception as input is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws NiteException {
        throw new NiteException(String.format(
                "I'm sorry, but I don't know what \"%s\" means :-(\n"
                + "    type \"help\" for a list of commands!", input));
    }
}
