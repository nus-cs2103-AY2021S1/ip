package duke;

import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> store;

    public TaskList(ArrayList<Task> store) {
        this.store = store;
    }
    public void addTask(Task task) {
        store.add(task);
    }

    public void deleteTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= store.size()) {
            throw new MissingTaskException();
        }
        Task task = store.remove(taskIndex);
        System.out.println("duke.Task deleted:");
        System.out.println(task);
    }
    public void completeTask(Integer taskIndex) {
        // If the task doesn't exist (It's index is missing)
        if (taskIndex < 0 || taskIndex >= store.size()) {
            throw new MissingTaskException();
        }

        // Set the task to done
        Task task = store.get(taskIndex);
        task.done();
        System.out.println("duke.Task marked as complete:");
        System.out.println(task);
    }

    public void list() {
        for (int i = 0; i < store.size(); i++) {
            String listText = String.format("%d. %s", i + 1, store.get(i));
            System.out.println(listText);
        }
    }

    public String dumpTasks() {
        // Format the store output as a string
        String data = "";
        for ( Task task : store ) {
            data += task.toString() + "\n";
        }
        return data;
    }
}
