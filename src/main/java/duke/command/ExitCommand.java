package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that exits the Duke program.
 */
public class ExitCommand extends Command {

    /**
     * Creates exit command.
     */
    public ExitCommand() {}


    /**
     * Prints goodbye message.
     * @param tasks TaskList that is being executed on.
     * @param ui Ui to update the user.
     * @param storage Storage to update the text file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printGoodbye();
    }

    /**
     * Returns true to exit program.
     * @return True to exit program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
