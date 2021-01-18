package duke.commands;

import duke.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.tasks.Task;

/**
 * Command sub-type to define deleting Tasks.
 */
public class DeleteCommand extends Command {

    /**
     * Create DeleteCommand object.
     *
     * @param attributes input attributes from user
     */
    public DeleteCommand(String attributes) {
        this.attributes = attributes;
    }

    /**
     * CLI (terminal) version of the command.
     *
     * @param taskList TaskList object handling the current list
     * @param storage Storage object to read/write from disk
     * @param ui Ui object to handle user interface interactions
     * @return true, continue program loop
     * @throws DukeException Duke-related exception while deleting a task from the list
     */
    @Override
    public boolean runCLI(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        runGUI(taskList, storage, ui);
        return true;
    }

    /**
     * Delete the task from the list.
     *
     * @param taskList TaskList object handling the current list
     * @param storage Storage object to read/write from disk
     * @param ui Ui object to handle user interface interactions
     * @return Duke output to GUI
     * @throws DukeException Duke-related exception while deleting a task from the list
     */
    @Override
    public String runGUI(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        Task deletedTask = taskList.deleteTask(Integer.parseInt(attributes));

        assert deletedTask != null : "Null return from deleting the task";
        storage.storeList(taskList.getList());
        return ui.writeDelete(deletedTask, taskList.getSize());
    }
}
