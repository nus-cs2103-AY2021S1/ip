package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class representing an exit command.
 */
public class ExitCommand extends Command {
    /**
     * Initiates the termination of the bot.
     *
     * @param tasks {@link TaskList} containing list of tasks.
     * @param ui {@link Ui} object.
     * @param storage {@link Storage} object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        assertArgumentsValid(tasks, ui, storage);
        ui.sayBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
