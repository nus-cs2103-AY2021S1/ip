package duke.commands;

import duke.util.Ui;
import duke.util.Storage;
import duke.util.DukeException;
import duke.tasks.TaskList;

import java.io.IOException;

/**
 * commands.Command that prompts duke.Duke to end and updates the storage.
 */
public class ExitCommand extends Command {

    /**
     * Prompt duke.Duke to end and update storage.
     * @param taskList TaskList containing Tasks.
     * @param ui Ui that handles system output.
     * @param storage Storage that handles file saving.
     * @throws IOException If file don't exist.
     * @throws DukeException If input is not recognised.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        isExit = true;
        ui.printExitMessage();
        super.execute(taskList, ui, storage);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
