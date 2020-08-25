package duke.command;

import duke.component.*;

public class ByeCommand implements Command {

    /**
     * Returns whether this is an exit command
     * @return true at all times
     */
    public boolean isExit() {
        return true;
    }

    /**
     * Says bye and closes Duke
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.giveResponse("Bye. Hope to see you again soon!");
    }
}
