package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     * @param taskList TaskList associated with command.
     * @param ui Ui associated with command.
     * @param storage Storage associated with command.
     * @throws DukeException If there is error during execution of command.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.exit();
    }

    /**
     * Indicates whether Duke chatbot is still running.
     * @return true.
     */
    public boolean isExit() {

        return true;
    }
}
