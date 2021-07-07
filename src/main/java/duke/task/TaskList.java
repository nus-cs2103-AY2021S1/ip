package duke.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.Consumer;

import duke.exceptions.DukeException;


/**
 * Represents a list that stores all tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Overloaded constructor that sets the user's task list as the task list from the database.
     *
     * @param tasks the list received from the database
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
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
        Task deletedTask = this.tasks.remove(index - 1);
        assert deletedTask != null : "Task is null";
        return deletedTask;
    }

    public int size() {
        return this.tasks.size();
    }

    public Task getTask(int index) throws DukeException {
        if (!isIndexInRange(index)) {
            throw new DukeException("Oh no! Task number does not exist in task list.");
        }
        Task task = this.tasks.get(index - 1);
        assert task != null : "Task is null";
        return task;
    }

    public void forEach(Consumer<Task> consumer) {
        this.tasks.forEach(consumer);
    }

    /**
     * Sorts the task list by priority HIGH, MEDIUM, LOW
     *
     * @return the task list object after being sorted by priority
     */
    public TaskList sortByPriority() {
        Collections.sort(this.tasks, new Comparator<Task>() {
            @Override
            public int compare(Task task1, Task task2) {
                return task1.priority.compareTo(task2.priority);
            }
        });
        return this;
    }

    public boolean isIndexInRange(int index) {
        return index <= this.tasks.size() && index > 0;
    }

}
