package duke.exec;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Exit implements Executable {

    // constants
    private static final String EXIT_MESSAGE = "Bye. "
            + "Hope to see you again soon!";

    /**
     * When run, prints the exit message
     * @param tasks the list of tasks
     * @param ui the ui object handling displays
     * @param store the storage object handling i/o
     */
    @Override
    public void run(TaskList tasks, Ui ui, Storage store) {
        ui.display(EXIT_MESSAGE);
    }
}
