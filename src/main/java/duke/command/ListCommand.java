package duke.command;

import duke.component.*;

public class ListCommand implements Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.displayList(taskList);
    }
}
