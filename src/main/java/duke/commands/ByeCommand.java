package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    public ByeCommand () {
        super();
        this.isExit = true;
    }

    @Override
    public void execute(Ui ui, Storage listStorage, TaskList taskList) {
        ui.goodBye();
    }
}
