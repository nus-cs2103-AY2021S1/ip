package duke.command;

import duke.TaskList;
import duke.ui.Ui;

/**
 * Exits the Duke program
 */
public class ExitCommand implements Command {
    
    public ExitCommand() {
    }
    
    /**
     * Prints out a farewell message
     *
     * @param tasks Current TaskList
     * @param ui    Where the User shall receive messages about the command
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.bidFarewell();
    }
    
    @Override
    public boolean isExit() {
        return true;
    }
}
