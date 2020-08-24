package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeUnknownInputException;

public class UnknownCommand implements Command {

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showError(new DukeUnknownInputException());
        return true;
    }
}
