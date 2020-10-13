package duke.command;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents an ExitCommand that is part of the Command class, regarding ending the chat.
 */

public class ExitCommand extends Command {

    /**
     * Constructor for the exit command.
     * @param command
     */
    public ExitCommand(String command) {
        super(command, true);
    }

    /**
     * Executes the command to exit out of a chat.
     *
     * @param list Tasklist containing tasks.
     * @param ui Ui for displaying output.
     * @param storage Storage of tasks in a txt file.
     */
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.finish();
    }

    @Override
    public String executeChat(TaskList list, Ui ui, Storage storage) {
        return ui.finish(true);
    }
}
