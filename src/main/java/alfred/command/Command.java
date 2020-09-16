package alfred.command;

import java.io.IOException;

import alfred.AlfredException;
import alfred.Storage;
import alfred.UI;
import alfred.task.TaskList;

/**
 * Abstract class that is inherited by all command classes.
 */
public abstract class Command {
    public abstract void execute(Storage storage, TaskList taskList, UI ui) throws AlfredException, IOException;
}
