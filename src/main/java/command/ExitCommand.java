package main.java.command;

import main.java.storage.Storage;
import main.java.task.TaskList;
import main.java.ui.Ui;

/**
 * EventCommand would execute the program when user specify
 * "exit" as the command. This would terminate the Duke
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
     * 1. Terminates the Duke program.
     * 2. Sends a farewell message to the user via Ui.
     * 3. Alter the setExit field to true.
     *
     * @param tasks TaskList List of task.
     * @param ui Ui updating user interface to show intended messages.
     * @param storage Storage to update external file whenever needed.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.getMessageTemplate(ui.formatMessage("Bye! Hope to see you soon"));

        super.setExit(true);
    }
}
