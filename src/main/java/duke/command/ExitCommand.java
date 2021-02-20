package duke.command;

import duke.core.MessageType;
import duke.core.Result;
import duke.core.Storage;
import duke.core.TaskList;
import duke.core.Ui;

/**
 * The ExitCommand class represents a command that exits the program.
 */
public class ExitCommand extends Command {

    /**
     * Takes in the task list, the interface, and the storage components, and exits
     * the program.
     *
     * @param taskList The task list component.
     * @param ui The user interface component.
     * @param storage The storage component.
     * @return The result of the execution of the command.
     */
    @Override
    public Result excecute(TaskList taskList, Ui ui, Storage storage) {
        return new Result(ui.showExit(), this.isContinuing(), MessageType.COMMAND_FOUND_MESSAGE);
    }

    /**
     * Returns the boolean value to indicates that this is an exit command.
     *
     * @return The boolean value of whether this command is an exit command.
     *         All the command will return true except for the exit cammand.
     */
    @Override
    public boolean isContinuing() {
        return false;
    }
}
