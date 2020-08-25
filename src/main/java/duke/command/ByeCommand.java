package duke.command;

import duke.Storage;
import duke.taskListHandler;
import duke.Ui;

/**
 * Inherits from generic command class.
 */
public class ByeCommand extends Command {

    /**
     * Terminates the program.
     *
     * @param handler Task list.
     * @param storage Storage instance.
     */
    @Override
    public void execute(taskListHandler handler, Storage storage) {
        System.out.println("Bye bye! Hope to see you again soon!");
        Ui.stopRunning();
    }
}
