package duke.command;

import duke.TaskList;
import duke.enums.Message;
import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Lists all the tasks that are in the TaskList
 */
public class ListCommand implements Command {
    
    public ListCommand(String[] parsedInput) {
    }
    
    
    /**
     * Prints out all the tasks in the TaskList
     *
     * @param tasks Current TaskList
     * @param ui    Where the User shall receive messages about the command
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        // involves making the ui print everything.
        ArrayList<Task> arr = tasks.getAllTasks();
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(Message.FETCHING_MSG.getMsg());
        for (Task t : arr) {
            lines.add(t.getID() + "." + t.toString());
        }
        ui.display(lines);
    }
    
    @Override
    public boolean isExit() {
        return false;
    }
}
