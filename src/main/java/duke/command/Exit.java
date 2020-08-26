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
    public void closeDuke(Storage storage) {
        storage.writeFile(tasks);
        layout.print("Bye. Hope to see you again soon!");
        System.exit(0);
    }
    
}
