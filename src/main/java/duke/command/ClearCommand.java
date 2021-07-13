package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command that will clear all the tasks in the task list.
 */
public class ClearCommand extends Command {
    /**
     * Clears all the tasks in the task list.
     *
     * @param tasks the task list.
     * @param ui the ui that will generate the cleared message.
     * @param storage the storage where the tasks will be saved.
     * @return the ui-generated message.
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) {
        tasks.clearTasks();
        storage.save(tasks.getTasks());
        return ui.generateClearedMessage();
    }

    /**
     * Indicates that this command is not an exit command.
     *
     * @return false since this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
