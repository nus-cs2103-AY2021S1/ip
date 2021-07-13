package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command that will terminate Duke.
 */
public class ExitCommand extends Command {
    /**
     * Terminates Duke.
     *
     * @param tasks the task list.
     * @param ui the ui that will generate the farewell message.
     * @param storage the storage where the tasks will be saved.
     * @return the ui-generated message.
     */
    @Override
    public String executeCommand(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks.getTasks());
        return ui.generateExitMessage();
    }

    /**
     * Indicates that this command is an exit command.
     *
     * @return true since this command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
