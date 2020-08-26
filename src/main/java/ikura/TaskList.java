// TaskList.java
// Copyright (c) 2020, zhiayang, Apache License 2.0.

package ikura;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.stream.Stream;

import java.io.IOException;

import ikura.task.Task;
import ikura.util.InvalidDatabaseException;

/**
 * A class encapsulating the list of tasks.
 */
public class TaskList {

    //* this can't be final, because the compiler is dumb
    private List<Task> tasks;
    private final Database db;

    /**
     * Constructs a TaskList, loading the tasks from disk from the given Database.
     *
     * @param db the Database to use.
     */
    public TaskList(Database db) {
        this.db = db;

        try {
            this.tasks = db.loadTasks();
        } catch (IOException e) {
            System.out.printf("error occured while reading/creating the task list:\n%s\n", e);
            this.tasks = new ArrayList<>();
        } catch (InvalidDatabaseException e) {
            System.out.printf("malformed line while reading task list:\n%s\n", e);
            this.tasks = new ArrayList<>();
        }
    }

    /**
     * Saves the list of tasks to disk.
     */
    public void save() {

        try {
            this.db.saveTasks(this.tasks);
        } catch (IOException e) {
            System.out.printf("failed to save task list to disk:\n%s\n", e);
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the number of tasks.
     */
    public int count() {
        return this.tasks.size();
    }

    /**
     * Returns the tasks as a Stream.
     *
     * @return a Stream of Tasks.
     */
    public Stream<Task> stream() {
        return this.tasks.stream();
    }

    /**
     * Returns the number of tasks in the list that are not completed.
     *
     * @return the number of undone tasks.
     */
    public long getNumPendingTasks() {
        return this.tasks.stream().filter(x -> !x.isDone()).count();
    }

    /**
     * Returns the number of tasks in the list that are completed.
     *
     * @return the number of done tasks.
     */
    public long getNumCompletedTasks() {
        return this.tasks.stream().filter(x -> x.isDone()).count();
    }

    /**
     * Adds a task to the list. The task should not be null.
     *
     * @param task the task to add.
     */
    public void addTask(Task task) {
        assert task != null;

        this.tasks.add(task);
    }

    /**
     * Returns the list of tasks, as a List.
     *
     * @return the list of tasks.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Removes a task from the list.
     *
     * @param task the task to remove.
     */
    public void removeTask(Task task) {
        this.tasks.remove(task);
    }

    /**
     * Removes all tasks from the list.
     */
    public void clearTasks() {
        this.tasks.clear();
    }

    /**
     * Gets a task with the given number. The number should be 1-indexed (ie. the first task is 1).
     *
     * @param number the number of the wanted task.
     * @return the Task if it exists, or an empty optional otherwise.
     */
    public Optional<Task> getTaskByNumber(int number) {
        // asdf
        try {
            return Optional.of(this.tasks.get(number - 1));
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }
    }
}
