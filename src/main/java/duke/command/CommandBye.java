package duke.command;

import duke.*;

/**
 * Command to exit.
 */
public class CommandBye implements Command {
    /**
     * Return <code>true</code> since the command is exit.
     * @return <code>true</code>
     */
    public boolean isExit() { return true; }

    /**
     * Execute the command by printing goodbye.
     * @param tasks the task list to be executed on
     * @param ui the <code>Ui</code> object to handle interface updates
     */
    public void execute(TaskList tasks, Ui ui) {
        ui.printLine("Bye. Hope to see you again soon!");
    }
}