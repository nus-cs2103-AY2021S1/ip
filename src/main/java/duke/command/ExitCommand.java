package duke.command;

import duke.MerchandiseList;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ExitCommand implements Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage,
                        MerchandiseList merchandises) {
        ui.setResponse("Bye. Hope to see you again soon!");
    }
}
