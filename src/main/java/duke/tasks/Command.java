package duke.tasks;

import java.io.IOException;

/**
 * Represents a Command. This Command executes the necessary input from
 * the user.
 */
public abstract class Command {
    public String execute(TaskList tasks, UI ui, Storage storage) throws IOException {
        return null;
    }

    /**
     * Returns if the command requires Duke to terminate.
     * @return if Duke is to be terminated.
     */
    public boolean isExit() {
        return false;
    }
}
