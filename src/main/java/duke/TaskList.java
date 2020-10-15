package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList implements java.io.Serializable {
    private final ArrayList<Task> store;

    public TaskList(ArrayList<Task> store) {
        this.store = store;
    }

    public TaskList() {
        this.store = new ArrayList<>();
    }

    /**
     * Adds a task
     * @param task
     */
    public void addTask(Task task) {
        store.add(task);
        assert store.size() >= 0;
    }

    /**
     * Delete a task
     * @param taskIndex
     */
    public void deleteTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= store.size()) {
            throw new MissingTaskException();
        }
        Task task = store.remove(taskIndex);
        assert store.size() >= 0;
        System.out.println("duke.Task deleted:");
        System.out.println(task);
    }

    /**
     * Complete a task
     * @param taskIndex
     * @return the result
     */
    public String completeTask(Integer taskIndex) {
        // If the task doesn't exist (It's index is missing)
        if (taskIndex < 0 || taskIndex >= store.size()) {
            throw new MissingTaskException();
        }

        // Set the task to done
        Task task = store.get(taskIndex);
        task.done();
        return String.format("duke task marked as complete: %s", task);
    }

    /**
     * List of tasks
     * @return
     */
    public String list() {
        String listText = "";
        for (int i = 0; i < store.size(); i++) {
            listText += String.format("\n %d. %s", i + 1, store.get(i));
        }
        return listText;
    }

    /**
     * Dump all the task as a formatted string
     * @return
     */
    public String dumpTasks() {
        // Format the store output as a string
        String data = "";
        for (Task task : store) {
            data += task.toString() + "\n";
        }
        return data;
    }

    /**
     * Update a task at the given index
     */
    public String updateTask(Integer taskIndex, Task updatedTask) {
        store.set(taskIndex, updatedTask);
        return "Updated task!";
    }

    /**
     * Find a task
     * @param text
     * @return
     */
    public ArrayList<Task> find(String text) {
        List<Task> matchedTasks = store.stream().filter(
            task -> task.getName().contains(text)
        ).collect(Collectors.toList());

        ArrayList<Task> result = new ArrayList<>();
        result.addAll(matchedTasks);

        return result;
    }
}
