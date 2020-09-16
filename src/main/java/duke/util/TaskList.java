package duke.util;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.FixedDurationTask;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.IntStream;

/**
 * The task list keeps track of all the tasks and has additional
 * functionalities including adding, removing, modifying tasks,
 * filtering, and formatting of the list for printing.
 */
public class TaskList {

    private final List<Task> list;

    /**
     * Constructs the task list with a given list.
     * @param list the list of tasks.
     */
    public TaskList(List<Task> list) {
        this.list = list;
    }

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Returns the size of the list.
     * @return the size of the list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Returns the task list object.
     * @return the task list object.
     */
    public List<Task> getList() {
        return list;
    }

    /**
     * The static factory method to create the subtypes of tasks.
     * @param type the subtype of the task to be created.
     * @param description the raw description of the task.
     * @return the created task object.
     * @throws DukeException when task creation fails.
     */
    public static Task createTask(String type, String description) throws DukeException {
        Task task = new Todo("");
        String[] split;
        String desc;
        if (description.isBlank()) {
            throw new DukeException("Description cannot be empty!");
        }
        switch (type) {
        case "todo":
            task = new Todo(description);
            break;
        case "deadline":
            if (!description.contains(" /by ")) {
                throw new DukeException("Oops, incorrect format!\nSay 'help' to learn about the commands!");
            }
            try {
                split = description.split(" /by ");
                desc = split[0];
                String by = split[split.length - 1];
                task = new Deadline(desc, by);
            } catch (IndexOutOfBoundsException ioobe) {
                throw new DukeException("Invalid " + type + " description!");
            }
            break;
        case "event":
            if (!description.contains(" /at ")) {
                throw new DukeException("Oops, incorrect format!\nSay 'help' to learn about the commands!");
            }
            try {
                split = description.split(" /at ");
                desc = split[0];
                String at = split[split.length - 1];;
                task = new Event(desc, at);
            } catch (IndexOutOfBoundsException ioobe) {
                throw new DukeException("Invalid " + type + " description!");
            }
            break;
        case "fixed":
            if (!description.contains(" /for ")) {
                throw new DukeException("Oops, incorrect format!\nSay 'help' to learn about the commands!");
            }
            try {
                split = description.split(" /for ");
                desc = split[0];
                String duration = split[split.length - 1];
                task = new FixedDurationTask(desc, duration);
            } catch (IndexOutOfBoundsException ioobe) {
                throw new DukeException("Invalid " + type + " description!");
            }
        }
        return task;
    }

    /**
     * Adds a task to the task list.
     * @param task the task to be added.
     */
    public void add(Task task) {
        list.add(task);
    }

    /**
     * Gets the task from the task list using a task number.
     * @param i the index of the task.
     * @return the task.
     */
    public Task get(int i) {
        return list.get(i);
    }

    /**
     * Removes and returns the task of the given task number.
     * @param i the index of the task to be removed.
     * @return the removed task.
     * @throws DukeException when the index does not contain a task.
     */
    public Task remove(int i) throws DukeException {
        try {
            i--;
            return list.remove(i);
        } catch (IndexOutOfBoundsException ioobe) {
            throw new DukeException("Invalid task number!");
        }
    }

    /**
     * Removes all tasks from the task list.
     */
    public void removeAll() {
        list.clear();
    }

    /**
     * Removes all tasks that are done.
     */
    public void removeDoneTasks() {
        list.removeIf(Task::getDone);
    }
    /**
     * Replaces a task at the given index.
     * @param i the index.
     * @param task the new task.
     */
    public void replace(int i, Task task) {
        list.set(i, task);
    }

    /**
     * Marks a task as done and returns the task.
     * @param i the index of the task.
     * @return the task.
     * @throws DukeException when the index does not contain a task.
     */
    public Task markDone(int i) throws DukeException {
        try {
            i--;
            Task task = list.get(i);
            if (task.getDone()) {
                throw new DukeException("Task is already done!");
            } else {
                task.markAsDone();
            }
            return task;
        } catch (IndexOutOfBoundsException ioobe) {
            throw new DukeException("Invalid task number!");
        }
    }

    /**
     * Gets the formatted message of the task list for printing.
     * @return the formatted print message of the list.
     */
    public String getPrintMessage() {
        if (list.isEmpty()) {
            return "Your list is empty!";
        }
        String msg = (list.size() > 1
                ? "Here are the tasks in your list:\n"
                : "Here's your one and only task:\n");

        msg += IntStream.range(0, list.size())
                .mapToObj(i -> String.format("%d. %s", i + 1, list.get(i)))
                .reduce((t1, t2) -> String.format("%s\n%s", t1, t2))
                .orElse("");

        return msg;
    }

    /**
     * Gets the formatted message of the filtered task list for printing.
     * @param query the keyword to filter tasks.
     * @return the formatted print message of the filtered list.
     */
    public String getFilteredListMessage(String query) {
        Optional<String> filtered = IntStream.range(0, list.size())
                .mapToObj(i -> String.format("%d. %s", i + 1, list.get(i)))
                .filter(s -> Arrays.asList(s.split(" ")).contains(query))
                .reduce((t1, t2) -> String.format("%s\n%s", t1, t2));

        if (filtered.isEmpty()) {
            return "Search result is empty!";
        }
        return "Search result:\n" + filtered.get();
    }

    /**
     * Sorts the task list based on the input parameter.
     * @param sortBy the parameter of sorting the list.
     */
    public void sort(String sortBy) throws DukeException {
        switch (sortBy) {
        case "name":
            sortByName();
            break;
        case "type":
            sortByType();
            break;
        case "datetime":
            sortByDateTime();
            break;
        default:
            throw new DukeException("Invalid sorting parameter");
        }
    }

    /**
     * Sorts the task list by the task description.
     */
    public void sortByName() {
        list.sort(Comparator.comparing(Task::getDescription));
    }

    /**
     * Sorts the task list by the task type.
     */
    public void sortByType() {
        list.sort(Comparator.comparing(Task::getType));
    }

    /**
     * Sorts the task list by task's the date time.
     */
    public void sortByDateTime() {
        list.sort(new TaskDateTimeComparator());
    }
}
