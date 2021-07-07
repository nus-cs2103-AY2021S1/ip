package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * ExitCommand class that represents exit commands
 */
public class ExitCommand extends Command {

    /**
     * ExitCommand Class constructor
     *
     * @param command the command from the user
     */
    public ExitCommand(String command) {
        super(command);
    }

    /**
     * Method that execute the current ExitCommand object
     *
     * @param list     TaskList object from the current Duke instance
     * @param ui       UI object from the current Duke instance
     * @param saveData Storage object from the current Duke instance
     */
    public String execute(TaskList list, Ui ui, Storage saveData) {
        ui.byeMessage();
        return "Bye. Hope to see you again soon!!!";
    }

    /**
     * Method that return true
     *
     * @return boolean object true
     */
    public boolean isExit() {
        return true;
    }
}
