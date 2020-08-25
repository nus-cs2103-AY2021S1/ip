package duke;

import duke.exception.WriteToStorageException;
import duke.task.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of Task objects. Contains functions to manipulate tasklist.
 */
public class TaskList {
    private Storage store;
    private List<Task> tasks;


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
    public Task remove(int index) {
        return tasks.remove(index);
    }
}
