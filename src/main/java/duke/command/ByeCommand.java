package duke.command;

import duke.util.TaskList;
import duke.util.Ui;

public class ByeCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    public void execute(TaskList taskList, Ui ui) {
        ui.print("Bye. Hope to see you again soon!");
    }
}
