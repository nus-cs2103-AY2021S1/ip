package stub;

import duke.command.Command;
import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.logic.UIManager;

public class CommandChildStub extends Command {

    public CommandChildStub(boolean isExit) {
        super(isExit);
    }

    @Override
    public void execute(TaskList taskList, UIManager uiManager, StorageManager storageManager) {
        System.out.println("Testing Command");
    }
}
