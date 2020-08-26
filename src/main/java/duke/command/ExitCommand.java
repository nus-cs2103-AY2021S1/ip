package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Class representing an exit command.
 */
public class ExitCommand extends Command {
    /**
     * Initiates the termination of the bot.
     * @param tasks List of tasks.
     * @param ui Ui object.
     * @param storage Storage object.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.sayBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
