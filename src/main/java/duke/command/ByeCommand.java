package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Class to exit the bot.
 */
public class ByeCommand extends Command {
    /**
     * Creates n ByeCommand to initiate termination of bot.
     */
    public ByeCommand() {
        super();
        this.isExit = true;
    }

    /**
     * Save Tasks and displays farewell message to user.
     *
     * @param ui Ui object to print messages to user.
     * @param storage Storage object to store items in the TaskList.
     * @param tasks Current list of tasks.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) {
        storage.saveData(tasks.getTasks());
        return ui.sayFarewell();
    }

}
