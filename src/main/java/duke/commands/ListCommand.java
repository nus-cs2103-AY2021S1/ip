package duke.commands;

import duke.DukeException;
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

    @Override
    public String runNew(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        return ui.writeOutput(taskList.listTasks());
    }
}
