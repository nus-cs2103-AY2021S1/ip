package duke.commands;

import duke.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;
import duke.tasks.Task;

/**
 * Command sub-type to define marking Tasks as done.
 */
public class DoneCommand extends Command {

    /**
     * Create DoneCommand object.
     *
     * @param attributes input attributes from user
     */
    public DoneCommand(String attributes) {
        this.attributes = attributes;
    }

    /**
     * CLI (terminal) version of the command.
     *
     * @param taskList TaskList object handling the current list
     * @param storage Storage object to read/write from disk
     * @param ui Ui object to handle user interface interactions
     * @return true, continue program loop
     * @throws DukeException Duke-related exception while marking a task as done
     */
    @Override
    public boolean runCLI(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        runGUI(taskList, storage, ui);
        return true;
    }

    /**
     * Mark a task as done.
     *
     * @param taskList TaskList object handling the current list
     * @param storage Storage object to read/write from disk
     * @param ui Ui object to handle user interface interactions
     * @return Duke output to GUI
     * @throws DukeException Duke-related exception while marking a task as done
     */
    @Override
    public String runGUI(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        Task markedTask = taskList.markDone(Integer.parseInt(attributes));

        assert markedTask != null : "Null return from marking the task";
        storage.storeList(taskList.getList());
        return ui.writeDone(markedTask);
    }
}
