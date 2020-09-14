package olivia.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import olivia.task.Task;

/**
 * TaskList class that stores the Tasks in the Duke object.
 */

public class TaskList {

    private final List<Task> tasks;

    /**
     * Overloaded constructor that creates an empty TaskList object.
     */

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Overloaded constructor that takes in a List of Tasks and creates a TaskList object
     * and populates it with the Tasks in the List.
     *
     * @param tasks a List containing Tasks to store in the TaskList
     */

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a Task to the TaskList.
     *
     * @param task the Task to be added.
     */

    public void addTask(Task task) {
        assert (task != null);
        this.tasks.add(task);
    }

    /**
     * Completes a Task in the TaskList.
     *
     * @param index the index of the Task to be completed.
     */

    public void completeTask(int index) {
        this.tasks.get(index - 1).complete();
    }

    /**
     * Deletes a Task in the TaskList.
     *
     * @param index the index of the Task to be deleted.
     */

    public void deleteTask(int index) {
        this.tasks.remove(index - 1);
    }

    /**
     * Retrieves a Task in the TaskList.
     *
     * @param index the index of the Task to be retrieved.
     * @return the Task retrieved at the associated index.
     */

    public Task getTask(int index) {
        return this.tasks.get(index - 1);
    }

    /**
     * Parses a given List of Tasks, returning a TaskList containing the Tasks found.
     *
     * @param tasks the List of Tasks to parse
     * @return a TaskList object containing the Tasks in the List
     */

    public static TaskList parse(List<Task> tasks) {
        return new TaskList(tasks);
    }

    /**
     * Returns a List of Tasks with descriptions that match the given keyword.
     *
     * @param keyword a String representing the keyword to search for
     * @return a List of Tasks with descriptions that match the given keyword
     */

    public List<Task> search(String keyword) {
        return tasks.stream()
                .filter(t -> Arrays.asList(t.getDescription().split(" "))
                        .contains(keyword))
                .collect(Collectors.toList());
    }

    /**
     * Returns a Stream of the Tasks in the TaskList.
     *
     * @return a Stream of the Tasks in the TaskList
     */

    public Stream<Task> stream() {
        return this.tasks.stream();
    }

    /**
     * Returns the number of Tasks in the TaskList.
     * ß
     * @return the number of Tasks in the TaskList
     */

    public int size() {
        return this.tasks.size();
    }

}
