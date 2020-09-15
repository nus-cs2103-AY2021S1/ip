package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

/**
 * The help command provides users a way to learn how to use the program.
 * Available commands will be generated and shown to the user.
 */
public class HelpCommand implements Command {

    /**
     * Identifies if the command calls for an exit of the program.
     * @return the value of whether the command is an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command and generates the response message.
     * @param tasks the task list.
     * @param ui the ui.
     * @param storage the storage.
     * @return the response message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String message = ui.getListOfCommands();
        ui.sendMessage(message);
        return ui.getListOfCommands();
    }
}
