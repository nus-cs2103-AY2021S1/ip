package duke.task;

import duke.dukeException.DukeException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        list = new ArrayList<>();
    }

    public Task getTask(int id) throws DukeException {
        try {
            return list.get(id - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(" This task #" + id + " does not exist.");
        }
    }

    public void addTask(Task t) {
        list.add(t);
    }

    public void removeTask(int id) {
        list.remove(id - 1);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public int size() {
        return list.size();
    }

    public ArrayList<Task> getList() {
        return this.list;
    }
}
