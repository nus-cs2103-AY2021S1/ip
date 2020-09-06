package duke.command;

import duke.component.DukeException;
import duke.component.Storage;
import duke.component.TaskList;
import duke.component.Ui;

/**
 * Command that closes Duke.
 */
public class ByeCommand implements Command {

    /**
     * Returns whether this is an exit command.
     *
     * @return true at all times.
     */
    public boolean isExit() {
        return true;
    }

    /**
     * Says bye and closes Duke.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return ui.giveResponse("\tBye. Hope to see you again soon!");
    }
}
