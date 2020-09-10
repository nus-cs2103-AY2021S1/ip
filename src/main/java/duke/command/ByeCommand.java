package duke.command;

import duke.Task;
import duke.TaskList;
import duke.Ui;

public class ByeCommand extends Command {
    public ByeCommand() {
        response = new Ui().byeMessage();
    }
}
