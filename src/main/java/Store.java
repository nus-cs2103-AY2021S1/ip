import java.util.List;
import java.util.ArrayList;

public class Store {

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

    public String markAsDone(int index) {
        Task selected = store.get(index);
        selected.setCompleted();
        return String.format("Nice! I've marked this task as done:\n  %s\n", selected.toString());
    }

    public String listItems() {
        String list = "Here are the tasks in your list:\n";
        for (int i = 0; i < this.store.size(); i++) {
            list += String.format("%d.%s\n", i + 1, this.store.get(i).toString());
        }
        return list;
    }
}
