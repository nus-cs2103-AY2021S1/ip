package duke.command;

import duke.Ui;
import duke.task.TaskList;

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
