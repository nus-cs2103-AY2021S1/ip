package duke.task;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the list in which all the tasks are stored while Duke is running.
 */
public class TaskList {
    private List<Task> tasks;
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     * @param task The task that should be added to the list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Gets a specific task from the list.
     * @param index the number representing the task in the list.
     * @return a Task from the list.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Gets the number of tasks in the list.
     * @return an integer that represents the number of tasks in the list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Removes a task from the list.
     * @param index the number representing the task that should be removed.
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    /**
     * Iterates through the list and add them all to a string.
     * @return A string that contains every task currently in the list.
     */
    public String iterateList() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task current = tasks.get(i);
            builder.append(i + 1).append(". ").append(current).append("\n");
        }
        return builder.toString();
    }

    /**
     * Iterates through the list and add all relevant tasks to a string.
     * @param keyword the keyword that determines if a task is relevant or not.
     * @return A string containing all relevant tasks from the list.
     */
    public String iterateFind(String keyword) {
        assert keyword.length() > 0 : "Keyword should have at least 1 character";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task current = tasks.get(i);
            if (current.containsKeyword(keyword)) {
                builder.append(i + 1).append(". ").append(current).append("\n");
            }
        }
        return builder.toString();
    }

    /**
     * Clears the entire list of tasks.
     */
    public void newList() {
        this.tasks = new ArrayList<>();
    }
}

