package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand implements Command {
    static final String EMPTY_STRING = "";

    /**
     * Prints the goodbye message to the user
     *
     * @param tasks current list of tasks
     * @param ui user interface to show messages
     * @param storage storage interface
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) { return ui.format(EMPTY_STRING);
    }
}
