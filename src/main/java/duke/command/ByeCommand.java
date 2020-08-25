package duke.command;

import duke.component.*;

public class ByeCommand implements Command {

    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.giveResponse("Bye. Hope to see you again soon!");
    }
}
