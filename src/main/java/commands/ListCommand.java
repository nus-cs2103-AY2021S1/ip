package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import exceptions.InvalidListCommandException;

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
