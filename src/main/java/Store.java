import java.util.List;
import java.util.ArrayList;

public class Store {

    private List<Task> store;

    Store() {
        this.store = new ArrayList<>();
    }

    public void add(Task item) {
        this.store.add(item);
        System.out.printf("     Got it. I've added this task:\n       %s\n", item.toString());
        System.out.printf("     Now you have %d tasks in your list.\n", this.store.size());
    }

    public void markAsDone(int index) {
        Task selected = store.get(index);
        selected.setCompleted();
        System.out.printf("     Nice! I've marked this task as done:\n"
                + "       %s\n", selected.toString());
    }

    public void listItems() {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < this.store.size(); i++) {
            System.out.printf("     %d.%s\n", i + 1, this.store.get(i).toString());
        }
    }
}
