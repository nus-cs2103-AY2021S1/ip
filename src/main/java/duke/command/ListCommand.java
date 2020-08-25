package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskListHandler;

public class ListCommand extends Command {
    @Override
    public void execute(TaskListHandler handler, Storage storage) {
        // Prints the given list
        try {
            handler.printList();
        } catch (DukeException e) {
            e.printStackTrace(System.out);
        }
    }
}
