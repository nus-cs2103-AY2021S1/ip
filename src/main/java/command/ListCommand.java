package command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;

public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.displayTaskList(taskList);
    }

    public ListCommand() {
        super(Command.CommandType.List);
    }
}
