package duke.command;

import duke.parts.Storage;
import duke.parts.TaskList;
import duke.parts.Ui;

/**
 * Represents a command which is used to sort the list of tasked
 * It is executed when the execute method is called.
 *
 * @author Roger Lim
 */
public class SortCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.sort(storage);
        return ui.printSorted();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
