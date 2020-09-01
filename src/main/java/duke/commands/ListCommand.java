package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command sub-type to define listing current Tasks.
 */
public class ListCommand extends Command {

    /**
     * Creates ListCommand object.
     */
    public ListCommand() {}

    @Override
    public boolean run(TaskList taskList, Storage storage, Ui ui) {
        ui.writeOutput(taskList.listTasks());
        return true;
    }
}
