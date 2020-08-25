package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand implements Command {
    static final String BYE_MESSAGE = "Bye. Hope to see you again soon!";

    /**
     * Prints the goodbye message to the user
     *
     * @param tasks current list of tasks
     * @param ui user interface to show messages
     * @param storage storage interface
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print(BYE_MESSAGE);
    }
}
