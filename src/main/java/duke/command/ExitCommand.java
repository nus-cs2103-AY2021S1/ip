package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        isExit = true;
    }
    /**
     * Executes the exit command.
     * @param taskList TaskList associated with command.
     * @param ui Ui associated with command.
     * @param storage Storage associated with command.
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.exit();
    }
}
