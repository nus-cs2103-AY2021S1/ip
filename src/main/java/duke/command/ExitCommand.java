package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Represents command that is specific to the exit command.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        this.exit = true;
    }

    /**
     * Executes exit command of user.
     * @param taskList tasks of user.
     * @param ui user interface object.
     * @param storage Storage object to retrieve and store data from file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printByeMessage();
    }
}
