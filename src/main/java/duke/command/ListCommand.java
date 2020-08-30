package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
    public CommandResult execute(TaskList tasks, Ui ui, Storage storage) {
        return new CommandResult(ui.printTasksChatWindow(tasks.getTasks()));
    }

}
