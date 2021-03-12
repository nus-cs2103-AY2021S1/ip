package duke.command;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

/**
 * HelpCommand class to execute command to provide in-App guidance to user.
 */
public class HelpCommand extends Command {

    public HelpCommand(String input) {
        super(input);
    }

    /**
     * Execute the exit process.
     *
     * @param tasks TaskList of tasks.
     * @param ui Ui object from the Ui class.
     * @param storage Storage object from the Storage class.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showHelpMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
