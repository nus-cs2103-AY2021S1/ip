package duke.command;

import duke.task.TaskList;
import duke.ui.Ui;

/**
 * This Command will cause Duke to exit.
 */
public class ByeCommand implements Command {
    @Override
    public void execute(Ui ui, TaskList list) {
        ui.say("Bye! Hope to see you again soon!");
        ui.stop();
    }
}
