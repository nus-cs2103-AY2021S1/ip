package duke.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> items;

    public TaskList() {
        this.items = new ArrayList<>();
    }

    public TaskList(List<Task> items) {
        this.items = items;
    }

    public List<Task> getItemsList() {
        return new ArrayList<>(this.items);
    }

    public int size() {
        return this.items.size();
    }

    public String add(Task task) {
        this.items.add(task);
        return "morning sir i have added this to the list sir:\n"
            + task
            + "\ni counted all your number of tasks sir it is "
            + this.items.size()
            + " sir";
    }

    /**
     * Formats a List of Tasks into a human readable list.
     * Ignore null indices. This is used for the find command.
     * @param items List of items to print.
     * @return formatted String.
     */
    public static String enumerateItems(List<Task> items) {
        StringBuilder numberedItems = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            Task item = items.get(i);
            if (item != null) {
                numberedItems.append(i + 1).append(". ").append(item).append("\n");
            }
        }
        return numberedItems.toString();
    }

    public String toString() {
        return enumerateItems(this.items);
    }

    public Task markItem(int idx) {
        Task selected = this.items.get(idx);
        selected.markAsDone();
        return selected;
    }

    public Task deleteItem(int idx) {
        return this.items.remove(idx);
    }
}
