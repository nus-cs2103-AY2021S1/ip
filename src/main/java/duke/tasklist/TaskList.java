package duke.tasklist;

import duke.tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the list of tasks.
 * It has operations to add/delete tasks in the list.
 */
public class TaskList {
    private List<Task> list;

    /** Constructs an empty TaskList. */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Constructs a TaskList from the given list.
     *
     * @param list The List to be associated with the TaskList.
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    public Task get(int i) {
        return this.list.get(i);
    }

    public List<Task> getList() {
        return this.list;
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * Marks the i-th task (index i - 1) as done.
     *
     * @param i The index (starting from 1) of the task to be marked as done in the TaskList.
     * @return The task marked as done.
     */
    public Task setDone(int i) {
        this.list.get(i - 1).markAsDone();
        return this.list.get(i - 1);
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.list.add(task);
    }

    /**
     * Deletes the i-th task (index i - 1) from the TaskList.
     *
     * @param i The index (starting from 1) of the task to be deleted.
     * @return The deleted task.
     */
    public Task delete(int i) {
        return this.list.remove(i - 1);
    }

    /**
     *
     * @return The number of tasks in the TaskList.
     */
    public int size() {
        return this.list.size();
    }
 }
