package duke.command;

/**
 * Represents a command that says bye, aiming to exit the app.
 */

import duke.component.*;

public class ByeCommand extends Command {
    /**
     * Creates a command for exiting.
     * @param input The input command "bye".
     */
    public ByeCommand(String input) {
        super(input);
    }

    /**
     * Tells whether this command is aiming for exiting.
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the command, prints the current list on ui.
     * @param ui The user interface object that is currently running.
     * @param list The current list of tasks.
     * @param storage The storage-writing object that is currently running.
     * @return The string "bye".
     */
    @Override
    public String execute(Ui ui, TaskList list, Storage storage) {
        ui.printList(list, t -> true, "");
        return "bye";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ByeCommand;
    }
}
