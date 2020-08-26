package duke.command;
import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents an ExitCommand that is part of the Command class, regarding ending the chat.
 */

public class ExitCommand extends Command {
    public ExitCommand(String command) {
        super(command, true);
    }

    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.finsih();
    }
}
