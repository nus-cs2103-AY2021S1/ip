package duke.task;

import duke.exceptions.DukeException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskList implements Iterable<Task> {

    private List<Task> store;

    public TaskList() {
        store = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        store = list;
    }

    public Task add(Task item) {
        store.add(item);
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
        store = new ArrayList<>();
        return true;
    }

    public String listItems() {
        if (store.size() == 0) {
            return "Congratulations! You don't have any tasks left to do.";
        } else {
            String list = "Here are the tasks in your list:\n";
            for (int i = 0; i < store.size(); i++) {
                list += String.format("%d.%s\n", i + 1, store.get(i).toString());
            }
            return list;
        }
    }

    public int taskCount() {
        return store.size();
    }

    @Override
    public Iterator<Task> iterator() {
        return store.iterator();
    }
}
