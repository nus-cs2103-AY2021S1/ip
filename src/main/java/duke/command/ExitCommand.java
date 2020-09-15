package duke.command;

import duke.util.DukeException;
import duke.util.TaskList;
import duke.util.Ui;
import duke.util.Storage;

/**
 * The exit command flags the program to exit after execution.
 */
public class ExitCommand implements Command {

    /**
     * Identifies if the command calls for an exit of the program.
     * @return the value of whether the command is an exit command.
     */
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the command and generates the response message.
     * @param tasks the task list.
     * @param ui the ui.
     * @param storage the storage.
     * @return the response message.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
        return "Have a good day! See you!";
    }
}
