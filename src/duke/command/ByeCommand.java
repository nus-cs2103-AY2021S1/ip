package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand implements Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    public boolean isDone() {
        return true;
    }
}
