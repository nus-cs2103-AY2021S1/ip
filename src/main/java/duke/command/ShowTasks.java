package duke.command;

import duke.io.Layout;
import duke.task.Task;

import java.util.ArrayList;

public class ShowTasks extends Command {

    /**
     * Get task list and display it to the user.
     */
    @Override
    public String execute(ArrayList<Task> tasks, Layout layout) {
        return layout.printTaskList(false, tasks);
    }
    
}
