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
     * @param tasks the task list that contains the tasks to be saved before exiting Duke.
     * @param ui the ui that will display a farewell message.
     * @param storage the storage where the tasks are saved before exiting.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.save(tasks.getTasks());
        ui.showExit();
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
