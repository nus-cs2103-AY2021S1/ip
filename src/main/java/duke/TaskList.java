package duke;

import java.util.List;

/**
 * Manage list of all tasks
 */
public class TaskList {
    private final List<Task> lst;
    private final Storage storage;

    public TaskList() {
        storage = new Storage("data.txt");
        lst = storage.load();
    }

    private void addTodo(String desc) {
        desc = desc.trim();
        if (desc.isEmpty()) {
            throw new IllegalArgumentException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        lst.add(new Todo(desc));
    }

    private void addDeadline(String desc) {
        desc = desc.trim();
        if (desc.isEmpty()) {
            throw new IllegalArgumentException("☹ OOPS!!! Missing arguments for deadline task");
        }
        String newDesc = desc.substring(0, desc.indexOf('/') - 1);
        String time = desc.substring(desc.indexOf('/') + 4);
        lst.add(new Deadline(newDesc, time));

    }

    private void addEvent(String event) {
        event = event.trim();
        if (event.isEmpty()) {
            throw new IllegalArgumentException("☹ OOPS!!! Missing arguments for event task");
        }
        String newDesc = event.substring(0, event.indexOf('/') - 1);
        String time = event.substring(event.indexOf('/') + 4);
        lst.add(new Event(newDesc, time));
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
        Ui.addTask(lst);
        storage.write(lst);
    }

    /**
     * mark a task as done
     *
     * @param command raw String contains the task id to mark as done
     */
    public void markDone(String command) {
        int num = Integer.parseInt(command) - 1;
        lst.get(num).markDone();
        Ui.markDone(lst.get(num));
        storage.write(lst);
    }

    /**
     * delete a task from the list
     *
     * @param command raw String contains the task id to delete from the list
     */
    public void delete(String command) {
        int num = Integer.parseInt(command) - 1;
        Task cur = lst.remove(num);
        Ui.delete(cur, lst);
        storage.write(lst);
    }

    /**
     * list all tasks in the list
     */
    public void list() {
        Ui.list(lst);
    }
}