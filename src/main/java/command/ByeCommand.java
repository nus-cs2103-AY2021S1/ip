package command;
import tasklist.TaskList;
import ui.Ui;
import storage.Storage;

public class ByeCommand extends Command {
    public ByeCommand(){}

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.goodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
