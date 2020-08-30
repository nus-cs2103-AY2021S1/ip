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
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.printTasksChatWindow(tasks.getTasks());
    }

}
