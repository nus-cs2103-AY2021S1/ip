package duke.command;

import duke.task.TaskList;
import duke.utils.Ui;

/**
 * Represents command that stops the duke.KK from running upon execution.
        */
public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList tasks, Ui ui) {
        return "";
    }
}
