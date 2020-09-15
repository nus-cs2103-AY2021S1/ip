package duke.parts;

import java.util.ArrayList;
import java.util.Collections;

import duke.error.DeleteListEmptyException;
import duke.error.DeleteNegativeIndex;
import duke.error.DeleteOutOfBounds;
import duke.task.Task;

/**
 * A class to store and handle the tasks input by the user
 *
 * @author Roger Lim
 */
public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    void updateList(Storage storage) {
        storage.save(tasks);
    }

    /**
     * Add a task and update the storage of the system.
     * @param task Tasks to be added
     * @param storage The storage of the system
     */
    public void addTask(Task task, Storage storage) {
        tasks.add(task);
        updateList(storage);
    }
    /**
     * Deletes a task and update the storage of the system.
     * @param index Index of task to be deleted
     * @param storage The storage of the system
     */
    public Task deleteTask(int index, Storage storage)
            throws DeleteNegativeIndex, DeleteOutOfBounds, DeleteListEmptyException {
        if (tasks.size() == 0) {
            throw new DeleteListEmptyException();
        }
        if (index < 0) {
            throw new DeleteNegativeIndex();
        }

        if (index >= tasks.size()) {
            throw new DeleteOutOfBounds(index + 1);
        }
        assert index < tasks.size() && index >= 0;
        Task removed = tasks.remove(index);
        updateList(storage);
        return removed;
    }

    /**
     *
     * @return Number of tasks stored in the list.
     */
    public int numTask() {
        return tasks.size();
    }

    /**
     * Searches the list for tasks that matched the input string.
     * @param input substring that is used in the search.
     * @return An arraylist of tasks that match the input string.
     */
    public ArrayList<Task> find(String input) {
        ArrayList<Task> temp = new ArrayList<>();

        for (Task t: tasks) {
            if (t.contains(input)) {
                temp.add(t);
            }
        }
        return temp;
    }

    /**
     * Marks the tasks at this index as done.
     * @param index The index of the tasks to be marked as done.
     * @param storage The storage of the system.
     * @return The tasks after it has been updated.
     */
    public Task markDone(int index, Storage storage) {
        tasks.get(index).setDone();
        this.updateList(storage);
        return tasks.get(index);
    }

    /**
     * Sorts the list of tasks and updates the storage.
     * @param storage The storage of the system.
     */
    public void sort(Storage storage) {
        Collections.sort(tasks, new TaskComparator());
        this.updateList(storage);
    }

}
