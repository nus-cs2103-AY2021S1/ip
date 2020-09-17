package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import duke.exception.WriteToStorageException;
import duke.task.Task;


/**
 * Represents a list of Task objects. Contains functions to manipulate tasklist.
 */
public class TaskList {
    private Storage store;
    private List<Task> tasks;


    /**
     * To instantiate a TaskList, pass in Storage object.
     *
     * @param store  Storage Object to store Task data.
     */
    public TaskList(Storage store) {
        this.store = store;
        this.tasks = new ArrayList<>();
        store.initialiseTasks(tasks);
    }


    /**
     * Returns TaskList's Storage Object.
     *
     * @return TaskList's Storage Object.
     */
    public Storage getStore() {
        return store;
    }

    /**
     * Add task to TaskList
     *
     * @param task  Task to be added.
     * @throws WriteToStorageException  If error encountered when writing to db.
     */
    public void addTask(Task task) throws WriteToStorageException {
        tasks.add(task);
        try {
            store.writeData(task);
        } catch (IOException e) {
            throw new WriteToStorageException();
        }
    }
    /**
     * Mark a task in TaskList as done.
     * @param index Index of the task to be set as done.
     * @throws WriteToStorageException if Storage faces issues writing tasks.
     */
    public void doneTask(int index) throws WriteToStorageException {
        tasks.get(index).markAsDone();
        try {
            store.rewriteData(tasks);
        } catch (IOException e) {
            throw new WriteToStorageException();
        }
    }
    @Override
    public String toString() {
        if (tasks.size() == 0) {
            return "No task added yet!";
        } else {
            StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                result.append(i + 1).append(". ").append(tasks.get(i).toString()).append(i + 1 == tasks.size()
                        ? "" : "\n");
            }
            return result.toString();
        }
    }
    public Task getTask(int index) {
        return tasks.get(index);
    }
    public int size() {
        return tasks.size();
    }

    /**
     * Delete a task from list.
     * @param index Index of task to be removed.
     * @return return deleted Task object.
     */
    public Task remove(int index) throws WriteToStorageException {
        Task removedTask = tasks.remove(index);
        try {
            store.rewriteData(tasks);
        } catch (IOException e) {
            throw new WriteToStorageException();
        }
        return removedTask;
    }

    /**
     * Searches through TaskList to find all Task that contains query.
     *
     * @param query  Query to be searched
     * @return  returns list of tasks that contains query.
     */
    public List<Task> findAll(String query) {
        List<Task> ts = new ArrayList<>();
        for (Task t : tasks) {
            if (t.contains(query)) {
                ts.add(t);
            }
        }
        return ts;
    }

    /**
     * Checks whether TaskList contains a certain task.
     * @param t  Task to check against.
     * @return  Return true if list contains task.
     */
    public boolean contains(Task t) {
        for (Task task : tasks) {
            if (task.equals(t)) {
                return true;
            }
        }
        return false;
    }
}
