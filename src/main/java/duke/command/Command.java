package duke.command;

import duke.exception.InvalidInstructionException;
import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.logic.UIManager;

import java.io.IOException;

public abstract class Command {

    private final boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean getExitStatus() {
        return isExit;
    }

    public abstract void execute(TaskList taskList, UIManager uiManager, StorageManager storageManager)
            throws InvalidInstructionException, IOException;
}
