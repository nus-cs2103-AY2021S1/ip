package duke.command;

import duke.parts.Storage;
import duke.parts.TaskList;
import duke.parts.Ui;

import java.io.IOException;

/**
 * Represents a command which is used to print the list of tasks.
 * It is executed when the execute method is called.
 */
public class PrintCommand extends Command{

    /**
     * Executes the print command.
     * THe method prints the list of tasks which is stored in storage.
     * @param tasks The task list of the system.
     * @param ui The UI of the system which interacts with user.
     * @param storage The storage of the system.
     * @throws IOException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        ui.printList(storage);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
