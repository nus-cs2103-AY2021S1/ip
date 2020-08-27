package duke.command;

import duke.exception.InvalidInstructionException;
import duke.logic.StorageManager;
import duke.logic.TaskList;
import duke.logic.UIManager;

import java.io.IOException;

/**
 * Represents a Command by the user.
 * It contains a <code>boolean</code> isExit to track if the program should terminate.
 * It also contains an abstract method <code>execute</code> for implementing classes.
 * Commands must have a method to execute their respective commands.
 */
public abstract class Command {

    private final boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Returns a <code>Boolean</code> denoting if the given program should exit.
     *
     * @return boolean isExit.
     */
    public boolean getExitStatus() {
        return isExit;
    }

    /**
     * Executes the user's command.
     * In most cases, it should validate the user's input as valid
     * before directing other sub-units to execute their respective tasks.
     *
     * @param taskList       <code>TaskList</code> object containing the user's <code>DukeTask</code>.
     * @param uiManager      <code>UIManager</code> object to handle printing feedback to user.
     * @param storageManager <code>StorageManager</code> object to saving/loading user data.
     * @throws InvalidInstructionException If user input validation fails.
     * @throws IOException                 If saving of data fails.
     */
    public abstract void execute(TaskList taskList, UIManager uiManager, StorageManager storageManager)
            throws InvalidInstructionException, IOException;
}
