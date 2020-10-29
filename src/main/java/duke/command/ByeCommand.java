package duke.command;

import duke.Storage;
import duke.TaskListHandler;
import duke.Ui;

/**
 * Inherits from generic command class.
 */
public class ByeCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    public static final String COMMAND_USAGE = COMMAND_WORD + ": Exits duke.\n"
        + "Example: " + COMMAND_WORD;

    /**
     * Terminates the program.
     *
     * @param handler Task list.
     * @param storage Storage instance.
     */
    @Override
    public void execute(TaskListHandler handler, Storage storage, String input) {
        System.out.println("Bye bye! Hope to see you again soon!");
        System.out.println();
        System.out.println("Duke closing... ");
        Ui.exitDuke();
    }
}
