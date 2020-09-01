package duke.command;

import duke.task.TaskList;
import duke.utils.Ui;

public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList tasks, Ui ui) {
        //do nothing
    }
}
