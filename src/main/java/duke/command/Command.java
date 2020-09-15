package duke.command;

import duke.io.Layout;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Abstract representation of all commands.
 */
public abstract class Command {

    public abstract String execute(ArrayList<Task> tasks, Layout layout);
    
}
