package Commands;

import Duke.Storage;
import Duke.TaskList;
import Duke.Ui;
import Exceptions.InvalidListCommandException;

public class ListCommand extends Command{
    public ListCommand(String fullCommand) {
        super(fullCommand);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws InvalidListCommandException {
        String[] tempArray = fullCommand.trim().split(" ");
        if (tempArray.length != 1) {
            throw new InvalidListCommandException();
        }
        ui.listMessage(taskList.toString());
    }
}
