package stub;

import duke.command.Command;
import duke.logic.CommandInteractionUi;
import duke.logic.StorageManager;
import duke.logic.TaskList;

/**
 * Represents a Command.
 * Used for testing
 */
public class CommandStub extends Command {
    @Override
    public boolean getExitStatus() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, CommandInteractionUi uiManager, StorageManager storageManager,
                        boolean isGuiTask) {
        if (isGuiTask) {
            this.response = "TESTING";
        } else {
            System.out.println("TESTING");
        }
    }
}
