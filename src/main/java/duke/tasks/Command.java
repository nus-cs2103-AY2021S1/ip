package duke.tasks;

import duke.tasks.*;

import java.io.IOException;

/**
 * Represents a Command. This Command executes the necessary input from
 * the user.
 */
public abstract class Command {
    public void execute(TaskList tasks, UI ui, Storage storage) throws IOException { }

    /**
     * Returns if the command requires Duke to terminate.
     * @return if Duke is to be terminated.
     */
    public boolean isExit() {
        return false;
    }
}
