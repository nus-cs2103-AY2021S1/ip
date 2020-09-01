package duke.command;

import duke.task.Task;

import java.util.ArrayList;

public class ShowTasks extends Command {
    
    public ShowTasks(ArrayList<Task> tasks) {
        super(tasks);
    }

    /**
     * Get task list and display it to the user.
     */
    public String showTasks() {
        return layout.printTaskList(false, tasks);
    }
    
}
