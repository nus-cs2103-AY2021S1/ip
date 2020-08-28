package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents an exit command.
 * @author Tee Kok Siang
 */
public class ByeCommand extends Command{
    /**
     * Constructs an ByeCommand object.
     */
    public ByeCommand() {
        super(true);
    }

    /**
     * Executes an ByeCommand to exit the Duke program.
     * Displays goodbye message.
     *
     * @param taskList List of tasks.
     * @param ui UI to handle user interaction.
     * @param storage Storage to save the task list in the hard disk.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showBye();
    }
}
