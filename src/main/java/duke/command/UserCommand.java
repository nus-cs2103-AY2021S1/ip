package duke.command;

import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the parent class of all commands.
 */
public abstract class UserCommand {

    /**
     * User's input.
     */
    protected String userInput;

    /**
     * Boolean to check if user wishes to exit the program
     */
    protected Boolean isExit = false;

    /**
     * @param userInput User's input.
     */
    public UserCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * @param taskList TaskList that contains all the existing tasks.
     * @param ui Ui that helps to print output.
     * @throws DukeException
     */
    public abstract String execute(TaskList taskList, Ui ui) throws DukeException;

    public Boolean getExit() {
        return isExit;
    }
}
