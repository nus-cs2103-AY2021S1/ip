package duke.command;

import duke.task.Task;

import java.util.List;

/**
 * Remove task from taskList
 */
public class DeleteCommand implements ReversibleCommand {

    private final List<Task> taskList;
    private final Task task;

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
     * Remove task from list
     */
    @Override
    public void execute() {
        this.taskList.remove(task);
        System.out.println("\t- Delete: " + task.toString());
    }

    /**
     * Add task to end of list
     */
    @Override
    public void reverse() {
        this.taskList.add(task);
        System.out.println("\t+ Undo Delete: " + task.toString());
    }

}
