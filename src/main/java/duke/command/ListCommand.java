package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskItems, Ui ui, Storage storage) throws DukeException {
        ui.printReply(ui.replyFormatter(taskItems.toString()));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
