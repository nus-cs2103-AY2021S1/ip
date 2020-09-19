package command;

import exception.AnonymousException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * WrongCommand class would execute the program when user specify
 * any command that doesn't exist in the program. It would notify
 * the user that the command doesn't exist.
 */
public class WrongCommand extends Command {

    private String command;

    /**
     * Constructs a WrongCommand with the given
     * user command.
     *
     * @param command String user command
     */
    public WrongCommand(String command) {
        super();
        this.command = command;
    }

    /**
     * Executes parsed user command. The result is:
     * 1. Notifies user that the input command does not exist in the program
     * and recommend the user to run the help command.
     * All the of the notification are run under Ui object.
     *
     * @param tasks TaskList List of task.
     * @param ui Ui updating user interface to show intended messages.
     * @param storage Storage to update external file whenever needed.
     * @throws AnonymousException Thrown when user run a command that
     * does not exist in the program.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AnonymousException {
        throw new AnonymousException(this.command);
    }
}
