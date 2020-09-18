package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This Command makes Duke report an error to the user. No special formatting is added.
 */
public class ErrorCommand implements Command {
    private String message;

    /**
     * Creates an ErrorCommand.
     *
     * @param message Error message for Duke to show.
     */
    public ErrorCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute(Ui ui, TaskList list) {
        ui.say(message, true);
    }
}
