package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.Storage;

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
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.onList(tasks.getList());
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
