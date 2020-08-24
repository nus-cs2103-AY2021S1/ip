package duke.command;

import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a command to stop the Duke program.
 */
public class ByeCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Prints goodbye message.
     * @param taskList TaskList which the Task is added to.
     * @param ui Ui which helps prints output.
     */
    public void execute(TaskList taskList, Ui ui) {
        ui.print("Bye. Hope to see you again soon!");
    }
}
