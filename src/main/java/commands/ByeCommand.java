package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exceptions.InvalidByeCommandException;

public class ByeCommand extends Command {

    public ByeCommand(String fullCommand) {
       super(fullCommand);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws InvalidByeCommandException {
        String[] tempArray = fullCommand.trim().split(" ");
        if (tempArray.length != 1) {
            throw new InvalidByeCommandException();
        }
        ui.byeMessage();
    }
}
