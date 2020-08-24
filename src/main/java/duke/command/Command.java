package duke.command;

import duke.*;

/**
 * Interface for all command classes.
 * This interface declares methods <code>execute()</code> and <code>isExit()</code>.
 */
public interface Command {
    void execute(TaskList tasks, Ui ui) throws Exception;
    boolean isExit();
}
