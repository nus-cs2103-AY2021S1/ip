package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class ExitCommand extends Command {
    public ExitCommand(String command) {
        super(command, true);
    }

    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.outro();
    }
}
