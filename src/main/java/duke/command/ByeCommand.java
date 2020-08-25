package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Responsible for executing a bye command.
 */
public class ByeCommand extends Command {
    public ByeCommand() {
        super(false);
    }

    /**
     * Executes a bye command.
     * 
     * @param tasks Contains the current tasks.
     * @param ui Responsible for displaying information to the user.
     * @param storage Reads and stores data into memory.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printResponse("Bye. Hope to see you again soon!");
    }
    
    @Override
    public String toString() {
        return "bye";
    }
}
