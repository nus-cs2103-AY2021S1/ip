package duke.command;

import duke.util.TaskList;
import duke.util.Ui;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui) {
        Command.isTerminated = true;
    }
}
