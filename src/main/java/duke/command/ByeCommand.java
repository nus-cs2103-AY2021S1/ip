package duke.command;

import duke.util.AliasMap;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

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
     * @param aliasMap alias mapping.
     * @return the execution message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, AliasMap aliasMap) {
        assert tasks != null && ui != null && storage != null;
        return ui.sayBye();
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
