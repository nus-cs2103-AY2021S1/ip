package command;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.DukeException;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showGoodbye();
        storage.saveTasks(taskList);
    }

    public ByeCommand() {
        super(Command.CommandType.Bye);
    }
}
