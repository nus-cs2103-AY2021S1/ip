package duke.command;

import duke.Storage;
import duke.TaskList;

/**
 * Command that displays a goodbye message and exits(quits) the bot when executed.
 */
public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Storage storage) {
        System.out.println("Ok bye bye! C u again :P");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
