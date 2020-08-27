package stub;

import duke.command.Command;
import duke.exception.InvalidInstructionException;
import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.logic.UIManager;

import java.io.IOException;

public class CommandChildStub extends Command {

    public CommandChildStub(boolean isExit) {
        super(isExit);
    }

    @Override
    public void execute(TaskList taskList, UIManager uiManager, StorageManager storageManager)
            throws InvalidInstructionException, IOException {
        System.out.println("Testing Command");
    }
}
