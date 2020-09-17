package duke;

import java.util.ArrayList;
import java.util.List;

import duke.dukeexception.DukeException;
import duke.task.Task;

/**
 * Contains the list of tasks based on user inputs
 * and handles operations on this list.
 */
public class TaskList {
    /** List object containing tasks represented by Task objects */
    private final List<Task> list;

    /**
     * Public constructor that creates a new (empty) list.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Public constructor taking in an existing list loaded from
     * the hard disk.
     *
     * @param list List loaded from the hard disk.
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to the list and updates the hard disk with this new task.
     *
     * @param task Task to be added.
     * @param storage Storage object that updates the relevant file on the hard disk.
     * @throws DukeException If exception is thrown by the update method of the Storage class.
     */
    public void addTask(Task task, Storage storage) throws DukeException {
        this.list.add(task);
        assert this.list.size() > 0 : "No task added.";
        boolean isFirstTask = this.list.size() <= 1;
        storage.update(task, isFirstTask);
    }

    /**
     * Deletes the task represented by a <code>taskNum</code> from the numbered list
     * displayed to the user. The task is the "taskNum"th task in the list when numbering
     * the tasks chronologically starting from 1 (not zero).
     *
     * @param taskNum Number that corresponds to the task to be deleted.
     * @param storage Storage object that updates the relevant file on the hard disk.
     * @throws DukeException If exception is thrown by the update method of the Storage class.
     */
    public void deleteTask(int taskNum, Storage storage) throws DukeException {
        assert taskNum > 0;
        this.list.remove(taskNum - 1);
        storage.update(this.list);
    }

    /**
     * Marks the task represented by a <code>taskNum</code> as done.
     * The task is the "taskNum"th task in the list when numbering
     * the tasks chronologically starting from 1 (not zero).
     *
     * @param taskNum Number that corresponds to the task to be marked.
     * @param storage Storage object that updates the relevant file on the hard disk.
     * @throws DukeException If exception is thrown by the update method of the Storage class.
     */
    public void markDone(int taskNum, Storage storage) throws DukeException {
        assert taskNum > 0;
        getTask(taskNum).markDone();
        storage.update(this.list);
    }

    /**
     * Returns the task represented by a <code>taskNum</code>.
     * The task is the "taskNum"th task in the list when numbering
     * the tasks chronologically starting from 1 (not zero).
     *
     * @param taskNum Number that corresponds to the task to be returned.
     * @return Task represented by the <code>taskNum</code>.
     */
    public Task getTask(int taskNum) {
        assert taskNum > 0;
        return this.list.get(taskNum - 1);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Current size of list.
     */
    public int getListLength() {
        return this.list.size();
    }

    @Override
    public String toString() {
        String finalString = "";
        int counter = 0;
        for (Task task : this.list) {
            counter++;
            finalString += "\n" + counter + "." + task.toString();
        }

        return finalString;
    }
}
