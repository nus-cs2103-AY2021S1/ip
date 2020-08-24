package duke.command;

import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.goodbye();
        System.exit(0);
    }
}
