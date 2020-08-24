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

    /**
     * Adds a new Task to the TaskList.
     * Returns a formatted String that should be printed to the user.
     * @param task Task to add.
     * @return String output to print.
     */
    public String add(Task task) {
        this.items.add(task);
        return "morning sir i have added this to the list sir:\n"
            + task
            + "\ni counted all your number of tasks sir it is "
            + this.items.size()
            + " sir";
    }

    @Override
    public String toString() {
        StringBuilder numberedItems = new StringBuilder();
        for (int i = 0; i < this.items.size(); i++) {
            numberedItems.append(i + 1).append(". ").append(this.items.get(i)).append("\n");
        }
        return numberedItems.toString();
    }

    /**
     * Calls the markAsDone method of the Task stored in this TaskList at index idx.
     * @param idx Index of Task to markAsDone.
     * @return Task of item that was just marked.
     */
    public Task markItem(int idx) {
        Task selected = this.items.get(idx);
        selected.markAsDone();
        return selected;
    }

    public Task deleteItem(int idx) {
        return this.items.remove(idx);
    }
}
