package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

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
     * @return the execution message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.onList(tasks.getList());
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
