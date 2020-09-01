package duke.command;

import duke.io.Storage;
import duke.task.Task;

import java.util.ArrayList;

public class Exit extends Command {
    
    public Exit(ArrayList<Task> tasks) {
        super(tasks);
    }
    
    /**
     * Store task list in hard disk.
     * Exit process.
     */
    public String closeDuke(Storage storage) {
        storage.writeFile(tasks);
        System.exit(0);
        return layout.print("Bye. Hope to see you again soon!");
    }
    
}
