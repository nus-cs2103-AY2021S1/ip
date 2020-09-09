package duke.command;

import duke.TaskList;
import duke.Ui;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
    }
}
