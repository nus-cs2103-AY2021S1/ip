package duke.command;

import duke.exception.DukeIoException;
import duke.exception.InvalidTaskIndexException;
import duke.exception.TaskDoneException;
import duke.logic.CommandInteractionUi;
import duke.logic.StorageManager;
import duke.logic.TaskList;


/**
 * Represents a Command by the user.
 * It contains a <code>boolean</code> isExit to track if the program should terminate.
 * It also contains an abstract method <code>execute</code> for implementing classes.
 * Commands must have a method to execute their respective commands.
 */
public abstract class Command {
    protected String response;

    public Command() {
        this.response = "";
    }

    /**
     * Returns a <code>Boolean</code> denoting if the given program should exit.
     *
     * @return boolean isExit.
     */
    public abstract boolean getExitStatus();

    /**
     * Returns a <code>String</code> denoting the response from executing command.
     *
     * @return String response
     */
    public String getResponse() {
        return this.response;
    }

    /**
     * Executes the user's command.
     * In most cases, it should validate the user's input as valid
     * before directing other sub-units to execute their respective tasks.
     * If the task is a form of GUI command, sets response to the result instead.
     *
     * @param taskList       <code>TaskList</code> object containing the user's <code>DukeTask</code>.
     * @param uiManager      <code>UIManager</code> object to handle printing feedback to user.
     * @param storageManager <code>StorageManager</code> object to saving/loading user data.
     * @param isGuiTask      <code>boolean</code> object to denote GUI task
     * @throws InvalidTaskIndexException If user input validation fails.
     * @throws TaskDoneException         If user input validation fails.
     */
    public abstract void execute(TaskList taskList, CommandInteractionUi uiManager,
                                 StorageManager storageManager, boolean isGuiTask)
            throws InvalidTaskIndexException, TaskDoneException, DukeIoException;
}
