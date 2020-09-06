package duke.task;

import java.util.ArrayList;
import java.util.function.Consumer;

import duke.exceptions.DukeException;


/**
 * Represents a list that stores all tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Overloaded constructor that sets the user's task list as the task list from the database.
     *
     * @param taskList the list received from the database
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Method to delete the task at the specified index.
     *
     * @param index is the index of the task to be deleted.
     * @return the task object that is being deleted.
     * @throws DukeException if the index specified by the user is invalid.
     */
    public Task deleteTask(int index) throws DukeException {
        if (!isIndexInRange(index)) {
            throw new DukeException("Oh no! Task number does not exist in task list.");
        }
        Task deletedTask = this.taskList.remove(index - 1);
        assert deletedTask != null : "Task is null";
        return deletedTask;
    }

    public int size() {
        return this.taskList.size();
    }

    public Task getTask(int index) throws DukeException {
        if (!isIndexInRange(index)) {
            throw new DukeException("Oh no! Task number does not exist in task list.");
        }
        Task task = this.taskList.get(index - 1);
        assert task != null : "Task is null";
        return task;
    }

    public void forEach(Consumer<Task> consumer) {
        this.taskList.forEach(consumer);
    }

    public boolean isIndexInRange(int index) {
        return index <= this.taskList.size() && index > 0;
    }

}
