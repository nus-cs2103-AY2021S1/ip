package duke.command;

import java.io.IOException;

import duke.common.DukeException;
import duke.common.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Executes the command.
     *
     * @param tasks list of tasks
     * @param ui object to output messages
     * @param storage object to write TaskList to file
     */
    public void execute (TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
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
