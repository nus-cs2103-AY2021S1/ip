package duke.command;

import duke.task.TaskList;
import duke.utils.Ui;

/**
 * Represents command that stops the Duke from running upon execution.
        */
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
