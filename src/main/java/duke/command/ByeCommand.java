package duke.command;

import duke.Task;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        super(new Ui().byeMessage());
    }
}
