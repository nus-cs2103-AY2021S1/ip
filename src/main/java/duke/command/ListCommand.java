package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.taskListHandler;

/**
 * Inherits from generic command class.
 */
public class ListCommand extends Command {

    /**
     * Prints the task list.
     *
     * @param handler Task list.
     * @param storage Storage instance.
     */
    @Override
    public void execute(taskListHandler handler, Storage storage) {
        // Prints the given list
        try {
            handler.printList();
        } catch (DukeException e) {
            e.printStackTrace(System.out);
        }
    }
}
