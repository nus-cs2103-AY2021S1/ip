package duke.task;

import duke.exceptions.DukeException;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class TaskList implements Iterable<Task> {

    private List<Task> store;

    public TaskList() {
        this.store = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.store = list;
    }

    public Task add(Task item) {
        this.store.add(item);
        return item;
    }

    public Task markAsComplete(int index) throws DukeException {
        try {
            Task selected = store.get(index);
            selected.setCompleted();
            return selected;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I couldn't find that task. Are you trying to make 2020 worse?");
        }
    }

    public Task deleteTask(int index) throws DukeException {
        try {
            Task removed = store.remove(index);
            return removed;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I couldn't find that task. Are you trying to make 2020 worse?");
        }
    }

    public boolean clearList() {
        this.store = new ArrayList<>();
        return true;
    }

    public String listItems() {
        String list = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.store.size(); i++) {
            list += String.format("%d.%s\n", i + 1, this.store.get(i).toString());
        }
        return list;
    }

    public int taskCount() {
        return this.store.size();
    }

    @Override
    public Iterator<Task> iterator() {
        return this.store.iterator();
    }
}
