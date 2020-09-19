package command;

import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * EventCommand would execute the program when user specify
 * "exit" as the command. This would terminate the duke.Duke
 * program.
 */
public class ExitCommand extends Command {

    /**
     * Constructs a ExitCommand without any
     * argument passed.
     */
    public ExitCommand() {
        super();
    }

    /**
     * Executes parsed user command. The result is:
     * 1. Terminates the duke.Duke program.
     * 2. Sends a farewell message to the user via Ui.
     * 3. Alter the setExit field to true.
     *
     * @param tasks TaskList List of task.
     * @param ui Ui updating user interface to show intended messages.
     * @param storage Storage to update external file whenever needed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        super.setExit(true);
        return ui.getMessageTemplate("Bye! Hope to see you soon");
    }
}
