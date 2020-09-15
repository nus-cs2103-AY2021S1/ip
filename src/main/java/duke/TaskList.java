package duke;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Manage list of all tasks
 */
public class TaskList {
    private final List<Task> tasks;
    private final Optional<Storage> storage;

    /**
     * Constructor for TaskList, do storage initialization
     */
    public TaskList(String filename) {
        storage = Optional.of(new Storage(filename));
        tasks = storage.get().load();
    }

    /**
     * Special constructor for TaskList with no storage
     * Used for testing only
     */
    public TaskList() {
        storage = Optional.empty();
        tasks = new ArrayList<>();
    }

    private void saveToDisk() {
        if (storage.isEmpty()) {
            return;
        }
        storage.get().write(tasks);
    }

    private void addTodo(String desc) {
        desc = desc.trim();
        if (desc.isEmpty()) {
            throw new IllegalArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        tasks.add(new Todo(desc));
    }

    private void addDeadline(String desc) {
        desc = desc.trim();
        if (desc.isEmpty()) {
            throw new IllegalArgumentException("☹ OOPS!!! Missing arguments for deadline task");
        }
        String newDesc = desc.substring(0, desc.indexOf('/') - 1);
        String time = desc.substring(desc.indexOf('/') + 4);
        tasks.add(new Deadline(newDesc, time));
    }

    private void addEvent(String event) {
        event = event.trim();
        if (event.isEmpty()) {
            throw new IllegalArgumentException("☹ OOPS!!! Missing arguments for event task");
        }
        if (event.contains("/between")) {
            String[] elements = event.split("/between");
            String[] times = elements[1].split("and");
            String newDesc = elements[0].trim();
            String happenAt = times[0].trim();
            String endAt = times[1].trim();
            tasks.add(new Event(newDesc, happenAt, endAt));
        } else {
            String newDesc = event.substring(0, event.indexOf('/') - 1);
            String time = event.substring(event.indexOf('/') + 4);
            tasks.add(new Event(newDesc, time));
        }
    }

    /**
     * Add an task to the task list
     * The method will classify the type of the task and call the corresponding method
     *
     * @param command raw string contains the detail of the task to be added
     */
    public void addTask(String command) {
        if (command.startsWith("todo")) {
            addTodo(command.substring(4));
        } else if (command.startsWith("deadline")) {
            addDeadline(command.substring(8));
        } else if (command.startsWith("event")) {
            addEvent(command.substring(5));
        }
        Ui.addTask(tasks);
        saveToDisk();
    }

    /**
     * mark a task as done
     *
     * @param command raw String contains the task id to mark as done
     */
    public void markDone(String command) {
        int num = Integer.parseInt(command) - 1;
        tasks.get(num).setDone();
        Ui.markDone(tasks.get(num));
        saveToDisk();
    }

    /**
     * delete a task from the list
     *
     * @param command raw String contains the task id to delete from the list
     */
    public void delete(String command) {
        int num = Integer.parseInt(command) - 1;
        Task cur = tasks.remove(num);
        Ui.delete(cur, tasks);
        saveToDisk();
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
