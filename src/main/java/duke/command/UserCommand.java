package duke.command;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the parent class of all commands.
 */
public abstract class UserCommand {

    /**
     * user's input.
     */
    protected String userInput;

    /**
     * boolean to check if user wishes to exit the program
     */
    public Boolean isExit = false;

    /**
     * @param userInput user's input.
     */
    public UserCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * @param taskList task list containing all the tasks.
     * @param ui       ui that prints output.
     * @throws DukeException
     */
    public abstract void execute(TaskList taskList, Ui ui) throws DukeException;

    /**
     * @return isExit boolean
     */
    public boolean isExit() {
        return isExit();
    }
}
