package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

/**
 * The Command class contains methods pertaining to the Command.
 *
 *  @author  Yen Pin Hsuan
 *  @version 1.0
 */
public abstract class Command {
    /**
     * The actions to be carried out after the command is given.
     * @param taskList TaskList containing all the tasks.
     * @param storage Storage object which handles storing of data.
     * @param ui Ui that interact with user.
     */
    public abstract void execute(TaskList taskList, Storage storage, Ui ui);

    /**
     * Return true if the command is ByeCommand.
     * @return True if the command is ByeCommand
     */
    public abstract boolean isExit();
}
