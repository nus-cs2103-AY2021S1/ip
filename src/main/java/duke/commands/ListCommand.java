package duke.commands;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Command sub-type to define listing current Tasks.
 */
public class ListCommand extends Command {

    /**
     * Creates ListCommand object.
     */
    public ListCommand() {}

    @Override
    public boolean runCLI(TaskList taskList, Storage storage, Ui ui) {
        runGUI(taskList, storage, ui);
        return true;
    }

    @Override
    public String runGUI(TaskList taskList, Storage storage, Ui ui) {
        return ui.writeOutput(taskList.listTasks());
    }
}
