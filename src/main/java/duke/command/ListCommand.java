package duke.command;

import duke.*;

/**
 * Represents a command to show the task list.
 */
public class ListCommand extends Command {

    /**
     * Class constructor.
     */
    public ListCommand() {
        super("list");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printTasksChatWindow(tasks.getTasks());
    }

}
