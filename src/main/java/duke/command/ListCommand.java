package duke.command;

import duke.*;

public class ListCommand extends Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.list(taskList);
    }
}
