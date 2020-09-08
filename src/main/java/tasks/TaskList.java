package tasks;

import java.util.ArrayList;

import exceptions.DukeIoException;
import utils.Storage;

public class TaskList {
    private final ArrayList<Task> tasks;
    private Storage storageObserver;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Add a given task to the list.
     *
     * @param task the task to add
     * @throws DukeIoException the duke io exception
     */
    public void add(Task task) throws DukeIoException {
        tasks.add(task);
        notifyObserver();
    }

    /**
     * Mark the task at a particular index as done.
     *
     * @param index the index of the task (assumed to be valid)
     * @throws DukeIoException the duke io exception
     */
    public void markAsDone(int index) throws DukeIoException {
        assert index < tasks.size() : "Invalid index";
        tasks.get(index).markAsDone();
        notifyObserver();
    }

    /**
     * Remove the task at a given index.
     *
     * @param index the index
     * @return the task
     * @throws DukeIoException the duke io exception
     */
    public Task remove(int index) throws DukeIoException {
        Task task = tasks.remove(index);
        notifyObserver();
        return task;
    }

    public void setObserver(Storage storage) {
        this.storageObserver = storage;
    }

    private void notifyObserver() throws DukeIoException {
        assert storageObserver != null : "No Observers";
        storageObserver.save(tasks);
    }

    public int size() {
        return tasks.size();
    }
}
