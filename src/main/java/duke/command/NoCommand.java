package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class NoCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.setResponse("I'm sorry, but I don't know what that means.");
    }
}
