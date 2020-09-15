package duke.command;

import java.io.IOException;

import duke.parts.Storage;
import duke.parts.TaskList;
import duke.parts.Ui;

/**
 * Represents a command which is used to print the list of tasks.
 * It is executed when the execute method is called.
 *
 * @author Roger Lim
 */
public class PrintCommand extends Command {

    /**
     * Executes the print command.
     * The method prints the list of tasks which is stored in storage.
     * @param tasks The task list of the system.
     * @param ui The UI of the system which interacts with user.
     * @param storage The storage of the system.
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        return ui.printList(storage);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
