package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.DukeException;


/**
 * Represents tasks of user.
 */
public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns list of tasks of user.
     * @return tasks of user.
     */
    public List<Task> getList() {
        return tasks;
    }

    /**
     * Adds task to the list of tasks of user.
     * @param task task which the user wants to add to the list.
     */
    public void addList(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task from list of tasks of user.
     * @param index Index of the task the user wants to delete.
     * @throws DukeException If index > number of tasks.
     */
    public void deleteTask(int index) throws DukeException {
        try {
            tasks.remove(index);
        } catch (Exception e) {
            throw new DukeException("You don't have such task in your list...");
        }
    }

    /**
     * Gets number of tasks.
     * @return number of tasks.
     */
    public int getListSize() {
        return tasks.size();
    }
}
