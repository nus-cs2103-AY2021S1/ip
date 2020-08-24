package duke;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents a list of Tasks for the Duke program.
 */
public class TaskList implements Serializable {
    protected ArrayList<Task> tasks;


    /**
     * Instantiates a TaskList with a specific List.
     * @param tasks the list to instantiate the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Instantiates a TaskList with no existing tasks.
     */
    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Adds a task to the TaskList.
     * @param task the task to be added
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a certain task in the TaskList as done.
     * @param index the 0-based index of the task to be marked as done
     * @return the task that was marked as done
     */
    public Task markTaskAsDone(int index) {
        Task task = tasks.get(index);
        task.setDone(true);
        return task;
    }

    /**
     * Returns the task at a certain 0-based index of the TaskList.
     * @param index the 0-based index of the task to be returned
     * @return the task at the 0-based index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes a task at the 0-based index of the TaskList
     * @param index the 0-based index of the task to be removed
     * @return the task that was removed
     */
    public Task remove(int index) {
        return tasks.remove(index);
    }

    /**
     * Returns a TaskList where all the terms' descriptions contain the search term
     * @param term the String to search for
     * @return the filtered TaskList
     */
    public TaskList find(String term) {
        ArrayList<Task> newList = new ArrayList<>(tasks);
        newList.removeIf((task -> !task.getDescription().contains(term)));
        return new TaskList(newList);
    }

    /**
     * Returns the size of the TaskList.
     * @return the size of the TaskList
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns true if the TaskList is empty.
     * @return true if the TaskList is empty
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TaskList taskList = (TaskList) o;
        return Objects.equals(tasks, taskList.tasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tasks);
    }
}
