import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

public class Store implements Iterable<Task> {

    private List<Task> store;

    Store() {
        this.store = new ArrayList<>();
    }

    public String add(Task item) {
        this.store.add(item);
        return String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in your list.\n",
                item.toString(),
                this.store.size());
    }

    public String markAsDone(int index) throws DukeException {
        try {
            Task selected = store.get(index);
            selected.setCompleted();
            return String.format("Nice! I've marked this task as done:\n  %s\n", selected.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I couldn't find that task. Are you trying to make 2020 worse?");
        }
    }

    public String deleteTask(int index) throws DukeException {
        try {
            Task removed = store.remove(index);
            return String.format("Noted. I've removed this task:\n  %s\nNow you have %d tasks in your list.",
                    removed.toString(),
                    this.store.size());
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("I couldn't find that task. Are you trying to make 2020 worse?");
        }
    }

    public String listItems() {
        String list = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.store.size(); i++) {
            list += String.format("%d.%s\n", i + 1, this.store.get(i).toString());
        }
        return list;
    }

    @Override
    public Iterator<Task> iterator() {
        return this.store.iterator();
    }
}
