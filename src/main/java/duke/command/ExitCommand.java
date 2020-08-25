package duke.command;

import duke.common.CustomException;
import duke.common.Ui;
import duke.storage.Storage;
import duke.task.TaskList;
import java.io.IOException;

/**
 * Command to exit the program.
 */
public class ExitCommand extends Command {
    public void execute (TaskList tasks, Ui ui, Storage storage) throws CustomException, IOException {
        Storage.writeToFile(tasks.getList());
        Ui.display("Bye! Hope to see you again! :D");
        System.exit(0);
    }

    /**
     * Checks if command will exit the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
