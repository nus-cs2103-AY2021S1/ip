package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.task.Ui;

public class ListCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage store) {
        ui.print(tasks.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
