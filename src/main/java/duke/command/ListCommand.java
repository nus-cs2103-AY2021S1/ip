package duke.command;

import duke.util.AliasMap;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a list command in the Duke program.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
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
        return ui.printList(tasks.getList());
    }

    /**
     * Checks whether an object is a list command.
     *
     * @param obj object to be compared with this command.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ListCommand;
    }
}
