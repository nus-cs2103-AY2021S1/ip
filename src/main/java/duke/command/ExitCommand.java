package duke.command;

import duke.TaskList;
import duke.ui.Ui;

/**
 * Exits the Duke program
 */
public class ExitCommand implements Command {
    public ExitCommand() {}
    /**
     * Initiates the System shutdown for the VM
     *
     * @param tasks Current TaskList
     * @param ui    Where the User shall receive messages about the command
     */
    @Override
    public String execute(TaskList tasks, Ui ui) {
        System.exit(0);
        return "Stub for ExitCommand.execute()";
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
