package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class ByeCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printMessage("Bye! See you again soon! :-)");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
