package duke.core.command;

import duke.designpattern.command.ReversibleExecutable;
import duke.core.task.Task;

import java.util.List;

/**
 * Add a task to taskList
 */
public class AddCommand implements ReversibleExecutable {

    private final List<Task> taskList;
    private final Task task;

    /**
     * Create an AddCommand which adds the Task into the TaskList
     * @param taskList which task will be added to
     * @param task to add into the taskList
     */
    public AddCommand(List<Task> taskList, Task task) {
        this.taskList = taskList;
        this.task = task;
        assert this.taskList != null;
        assert this.task != null;
    }

    /**
     * Add task to end of list
     */
    @Override
    public void execute() {
        // Disallow duplicate
        if (taskList.contains(task)) {
            System.err.println("Error: Duplicate Task");
            return;
        }

        this.taskList.add(task);
        System.out.println("\t+ Add: " + task.toString());
    }

    /**
     * Remove task from list
     */
    @Override
    public void reverse() {
        this.taskList.remove(task);
        System.out.println("\t- Undo Add: " + task.toString());
    }

}
