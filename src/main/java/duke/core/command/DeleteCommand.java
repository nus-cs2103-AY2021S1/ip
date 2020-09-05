package duke.core.command;

import duke.designpattern.command.ReversibleExecutable;
import duke.core.task.Task;

import java.util.List;

/**
 * Remove task from taskList
 */
public class DeleteCommand implements ReversibleExecutable {

    private final List<Task> taskList;
    private final Task task;
    private int removeIndex;

    /**
     * Create a DeleteCommand to remove the specified Task from the TaskList
     * @param taskList which specified task should be removed from
     * @param task to be removed from taskList
     */
    public DeleteCommand(List<Task> taskList, Task task) {
        this.taskList = taskList;
        this.task = task;
    }

    /**
     * Remove task from list.
     * If there is duplicate of the Task to be deleted in
     * taskList, DeleteCommand will remove the first matching
     * Task in the list
     */
    @Override
    public void execute() {
        this.removeIndex = this.taskList.indexOf(task);
        this.taskList.remove(removeIndex);
        System.out.println("\t- Delete: " + task.toString());
    }

    /**
     * Add task back into it's original index in the list.
     */
    @Override
    public void reverse() {
        this.taskList.add(removeIndex, task);
        System.out.println("\t+ Undo Delete: " + task.toString());
    }

}
