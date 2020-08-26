package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public class ExitCommand extends Command {
    ExitCommand(String command) {
        super(command);
    }
    public void execute(TaskList list, Ui ui, Storage saveData) {
        ui.byeMessage();
    }

    public boolean isExit() {
        return true;
    }
}
