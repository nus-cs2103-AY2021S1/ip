package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.ui.Ui;

/**
 * Represents an exiting application command.
 * @author Tee Kok Siang
 */
public class ByeCommand extends Command {
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
     * @return Formatted response message.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showBye();
        return Ui.EXIT_MESSAGE;
    }
}
