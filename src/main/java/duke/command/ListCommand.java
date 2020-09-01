package duke.command;

import duke.task.TaskList;
import duke.utils.Ui;

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
