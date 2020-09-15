package duke.command;

import duke.io.Layout;
import duke.io.Storage;
import duke.task.Task;

import java.util.ArrayList;

/**
 * Represents exit command.
 */
public class Exit extends Command {
    
    Storage storage;
    
    public Exit(Storage storage) {
        this.storage = storage;
    }

    /**
     * Store task list in hard disk.
     * Exit process.
     */
    @Override
    public String execute(ArrayList<Task> tasks, Layout layout) {
        storage.writeFile(tasks);
        return layout.print("Bye. Hope to see you again soon!");
    }
    
}
