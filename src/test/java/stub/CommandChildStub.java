package stub;

import duke.command.Command;
import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.logic.UiManager;

/**
 * Represents a stub of a Sub-Class of a Command during testing
 */
public class CommandChildStub extends Command {

    public CommandChildStub(boolean isExit) {
        super(isExit);
    }

    @Override
    public void execute(TaskList taskList, UiManager uiManager, StorageManager storageManager) {
        System.out.println("Testing Command");
    }
}
