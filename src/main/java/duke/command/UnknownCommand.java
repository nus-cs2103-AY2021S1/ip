package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.UnknownCommandException;

public class UnknownCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(new UnknownCommandException());
    }
}
