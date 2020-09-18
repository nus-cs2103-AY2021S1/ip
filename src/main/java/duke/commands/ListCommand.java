package duke.commands;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Command sub-type to define listing current Tasks.
 */
public class ListCommand extends Command {

    /**
     * Create ListCommand object.
     */
    public ListCommand() {}

    /**
     * List all the tasks currently in the list.
     *
     * @param taskList TaskList object handling the current list
     * @param storage Storage object to read/write from disk
     * @param ui Ui object to handle user interface interactions
     * @return true, continue program loop
     */
    @Override
    public boolean runCLI(TaskList taskList, Storage storage, Ui ui) {
        runGUI(taskList, storage, ui);
        return true;
    }

    /**
     * List all the tasks currently in the list.
     *
     * @param taskList TaskList object handling the current list
     * @param storage Storage object to read/write from disk
     * @param ui Ui object to handle user interface interactions
     * @return Duke output to GUI
     */
    @Override
    public String runGUI(TaskList taskList, Storage storage, Ui ui) {
        return ui.writeOutput(taskList.listTasks());
    }
}
