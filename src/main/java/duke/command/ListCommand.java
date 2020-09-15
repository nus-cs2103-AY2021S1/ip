package duke.command;

import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

/**
 * The list command helps to show the user all the tasks in a list
 * in a formatted manner.
 */
public class ListCommand implements Command {

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
        String msg = tasks.getPrintMessage();
        ui.sendMessage(msg);
        return msg;
    }
}
