package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeCommandException;
import duke.exception.DukeStorageException;


/**
 * A class to store commands.
 * Can be extended to implement different type of commands
 * and their specific ways of operating by overriding the
 * execute method.
 */
public abstract class Command {

    public String inputCommand;


    /**
     * A public constructor for a command.
     *
     * Requires an input command in the form of <code>[commandType] [arguments if any]</code>
     * @param inputCommand A command line of String type
     */
    public Command(String inputCommand) { this.inputCommand = inputCommand; }


    /**
     * Executes the command in field : inputCommand
     *
     * Execute the stored command line according to its command type.
     * The current list of tasks will be retrieved from list and outputs will
     * be printed using ui provided, then the changes made will be stored via storage
     * if necessary.
     * @param list A TaskList of tasks
     * @param storage storage used to handle load/save requests
     * @param ui the ui used to interact with user
     * @throws DukeCommandException  If the command received has incorrect format
     * @throws DukeStorageException  If fails to save changes made to the list of tasks
     */
    public abstract void execute(TaskList list, Storage storage, Ui ui) throws DukeCommandException, DukeStorageException;

    /**
     * Return true if the current command is an exit command. i.e. 'bye'
     * @return Whether the command is an exit command
     */
    public abstract boolean isExit();
}
