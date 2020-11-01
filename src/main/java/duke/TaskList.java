package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Manage list of all tasks
 */
public class TaskList {
    private final List<Task> tasks;
    private final Storage storage;

    /**
     * Constructor for TaskList, do storage initialization
     */
    public TaskList(String filename) {
        storage = new Storage(filename);
        tasks = storage.load();
    }

    /**
     * Special constructor for TaskList with no storage
     * Used for testing only
     */
    public TaskList() {
        storage = null;
        tasks = new ArrayList<>();
    }

    private void saveToDisk() {
        if (storage == null) {
            return;
        }
        storage.write(tasks);
    }

    /**
     * Add an task to the task list
     * The method will classify the type of the task and call the corresponding method
     *
     * @param command raw string contains the detail of the task to be added
     */
    public void addTask(String command) {
        tasks.add(Parser.parseTask(command));
        Ui.addTask(tasks);
        saveToDisk();
    }

    /**
     * mark a task as done
     *
     * @param command raw String contains the task id to mark as done
     */
    public void markDone(String command) {
        command = command.trim();
        int num = Integer.parseInt(command) - 1;
        try {
            tasks.get(num).setDone();
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
        Ui.markDone(tasks.get(num));
        saveToDisk();
    }

    /**
     * delete a task from the list
     *
     * @param command raw String contains the task id to delete from the list
     */
    public void delete(String command) {
        command = command.trim();
        try {
            int num = Integer.parseInt(command) - 1;
            Task cur = tasks.remove(num);
            Ui.delete(cur, tasks);
            saveToDisk();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * list all tasks in the list
     */
    public void find(String pattern) {
        List<Task> foundTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.desc.contains(pattern)) {
                foundTasks.add(task);
            }
        }
        Ui.find(foundTasks);
    }

    /**
     * list all tasks in the list
     */
    public void list() {
        Ui.list(tasks);
    }
}
