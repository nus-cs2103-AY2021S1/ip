package Duke.command;

import Duke.task.TaskList;
import Duke.utils.Ui;

public class ListCommand extends Command {

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.showListMessage(tasks);
    }
}
