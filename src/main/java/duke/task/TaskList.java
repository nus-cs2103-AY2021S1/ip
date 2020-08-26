package duke.task;

import duke.dukeException.DukeException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public Task getTask(int id) throws DukeException {
        try {
            return tasks.get(id - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" This task #" + id + " does not exist.");
        }
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void removeTask(int id) {
        tasks.remove(id - 1);
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getList() {
        return this.tasks;
    }
}
