package duke.commands;

import duke.tasks.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

import java.util.List;

/**
 * Command sub-type to define finding Tasks.
 */
public class FindCommand extends Command {

    /**
     * Creates FindCommand object.
     *
     * @param attributes input attributes from user
     */
    public FindCommand(String attributes) {
        this.attributes = attributes;
    }

    /**
     * CLI (terminal) version of the command
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
     * Finds all the tasks containing the search key.
     *
     * @param taskList TaskList object handling the current list
     * @param storage Storage object to read/write from disk
     * @param ui Ui object to handle user interface interactions
     * @return Duke output to GUI
     */
    @Override
    public String runGUI(TaskList taskList, Storage storage, Ui ui) {
        List<Task> foundTasks = taskList.findTasks(attributes);
        return ui.writeSearch(foundTasks);
    }
}
