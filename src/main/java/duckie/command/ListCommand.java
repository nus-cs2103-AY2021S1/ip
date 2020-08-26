package duckie.command;

import duckie.task.*;
import duckie.exception.*;
import duckie.Ui;
import duckie.Storage;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DuckieException {
        if (tasks.getTaskList().size() == 0) {
            throw new DuckieNoListException();
        } else {
            ui.displayListReply(tasks.getTaskList());
        }
    }
}
