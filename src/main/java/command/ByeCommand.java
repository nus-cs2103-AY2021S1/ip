package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;

public class ByeCommand extends Command {

    public ByeCommand() {
        super(Command.CommandType.Bye);
    }
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.saveTasks(taskList);
        return ui.showGoodbye();
    }
}
