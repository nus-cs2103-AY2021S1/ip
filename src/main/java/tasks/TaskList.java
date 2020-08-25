package tasks;

import exceptions.DukeIOException;
import utils.Storage;

import java.util.ArrayList;

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

    public void add(Task task) throws DukeIOException {
        tasks.add(task);
        notifyObserver();
    }

    public void markAsDone(int index) throws DukeIOException {
        tasks.get(index).markAsDone();
        notifyObserver();
    }

    public Task remove(int index) throws DukeIOException {
        Task task = tasks.remove(index);
        notifyObserver();
        return task;
    }

    public void setObserver(Storage storage) {
        this.storageObserver = storage;
    }

    private void notifyObserver() throws DukeIOException {
        storageObserver.save(tasks);
    }

    public int size() {
        return tasks.size();
    }
}
