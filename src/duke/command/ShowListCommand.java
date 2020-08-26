package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * ShowListCommand class that represents show list commands
 */
public class ShowListCommand extends Command {

    /**
     * ShowListCommand Class constructor
     * @param command the command from the user
     */
    public ShowListCommand(String command) {
        super(command);
    }

    /**
     * Method that execute the current ShowListCommand object
     * @param list TaskList object from the current Duke instance
     * @param ui    UI object from the current Duke instance
     * @param saveData Storage object from the current Duke instance
     */
    public void execute(TaskList list, Ui ui, Storage saveData) {
        ui.showList(list);
    }

    /**
     * Method that return isExit of the current Command
     * @return boolean object showing if Duke should terminate
     */
    public boolean isExit() {
        return isExit;
    }
}
