package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Command that displays the user's list of tasks when executed.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList tasks, Storage storage) {
        System.out.println("Na, here is your list lah:" + tasks.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
