package stub;

import duke.command.Command;
import duke.logic.CommandInteractionUi;
import duke.logic.StorageManager;
import duke.logic.TaskList;

/**
 * Represents a stub of a Sub-Class of a Command during testing
 */
public class CommandChildStub extends Command {

    public CommandChildStub(boolean isExit) {
        super(isExit);
    }

    @Override
    public void execute(TaskList taskList, CommandInteractionUi uiManager, StorageManager storageManager, boolean isGuiTask) {
        System.out.println("Testing Command");
    }
}
