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
    public void showTasks() {
        layout.printTaskList(false, tasks);
    }
    
}
