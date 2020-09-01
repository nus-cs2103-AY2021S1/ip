package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

/**
 * Represents a bye command in the Duke program.
 */
public class ByeCommand extends Command {

    /**
     * Checks whether the program is quitting.
     *
     * @return true.
     */
    @Override
    public boolean isQuitting() {
        return true;
    }

    /**
     * Executes the bye command.
     *
     * @param tasks TaskList of the program.
     * @param ui user interface of the program.
     * @param storage storage of the program.
     * @return the execution message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.farewell();
    }

    /**
     * Checks whether an object is a bye command.
     *
     * @param obj object to be compared with this command.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ByeCommand;
    }
}
