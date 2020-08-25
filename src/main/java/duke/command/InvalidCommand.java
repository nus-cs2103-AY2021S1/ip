package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskListHandler;

public class InvalidCommand extends Command {

    @Override
    public void execute(TaskListHandler handler, Storage storage) {
        // Other commands
        try {
            throw new DukeException("\u2639 Oops, I'm sorry but I don't know what that means :-(");
        } catch (DukeException e) {
            e.printStackTrace(System.out);
            DukeException.tryAgain();
        }
    }
}
