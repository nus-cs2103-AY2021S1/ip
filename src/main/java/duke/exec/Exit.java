package duke.exec;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Exit implements Executable {

    // constants
    private static final String EXIT_MESSAGE = "Bye. "
            + "Hope to see you again soon!";

    /**
     * When run, outputs the exit message
     *
     * @param tasks the list of tasks
     * @param ui the ui object handling displays
     * @param store the storage object handling i/o
     * @return output after running this command
     */
    @Override
    public String run(TaskList tasks, Ui ui, Storage store) {
        return ui.output(EXIT_MESSAGE);
    }
}
