package duke.task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.command.exception.DukeInvalidIndexException;
import duke.response.Response;

/**
 * Contains the list of tasks.
 * The <code>TaskList</code> object handles operations done to the list.
 */
public class TaskList {
    protected List<Task> list;

    /**
     * Class constructor.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Class constructor specified with a list of tasks.
     *
     * @param taskList List of tasks.
     */
    public TaskList(List<Task> taskList) {
        this.list = taskList;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added to the list.
     * @return The task that is added.
     */
    public Task addTask(Task task) {
        this.list.add(task);
        return task;
    }

    /**
     * Deletes a task from the list.
     *
     * @param index The index of the task in the list.
     * @return The task that is deleted.
     * @throws DukeInvalidIndexException If index < 0 or index >= list size.
     */
    public Task deleteTask(int index) throws DukeInvalidIndexException {
        try {
            Task toBeDeleted = get(index);
            this.list.remove(index);
            return toBeDeleted;
        } catch (DukeInvalidIndexException e) {
            throw e;
        }
    }

    /**
     * Marks a task in the list as done.
     *
     * @param index The index of the task in the list.
     * @return The task that has been marked as done.
     * @throws DukeInvalidIndexException If index < 0 or index >= list size.
     */
    public Task markDone(int index) throws DukeInvalidIndexException {
        try {
            Task task = get(index);
            task.markAsDone();
            return task;
        } catch (DukeInvalidIndexException e) {
            throw e;
        }
    }

    /**
     * Returns the String representing the tasks in the list.
     * Returns the alternative String given if list is empty.
     *
     * @param list The list of tasks.
     * @param alternative String returned if list is empty.
     * @return String representing the tasks in the list.
     */
    public static String getListAsStringFromList(List<Task> list, String alternative) {
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            s += Response.TWO_INDENT + (i + 1) + "." + list.get(i);
            if (i != list.size() - 1) {
                s += '\n';
            }
        }
        if (s.equals("")) {
            return alternative;
        } else {
            return s;
        }
    }

    /**
     * Returns String representing the tasks in the list.
     *
     * @return String representing the tasks in the list.
     */
    public String getListAsString() {
        return getListAsStringFromList(this.list,
                "There is nothing in the list!");
    }

    /**
     * Returns the size of the list of tasks.
     *
     * @return size of list of tasks.
     */
    public int getSize() {
        return list.size();
    }

    /**
     * Returns the task at the specific index given.
     *
     * @param index The index of the task in the list.
     * @return The task at the index.
     * @throws DukeInvalidIndexException If index < 0 or index >= list size.
     */
    public Task get(int index) throws DukeInvalidIndexException {
        try {
            return this.list.get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidIndexException();
        }
    }

    /**
     * Returns the String representing list of task on that date given.
     *
     * @param date The given date query.
     * @return The String representing the list of tasks.
     */
    public String getTasksOnDate(LocalDate date) {
        List<Task> tasksOnDate = new ArrayList<>();
        list.forEach(task -> {
            if (task instanceof Deadline) {
                Deadline d = (Deadline) task;
                if (d.getDate().isEqual(date)) {
                    tasksOnDate.add(d);
                }
            } else if (task instanceof Event) {
                Event e = (Event) task;
                if (e.getDate().isEqual(date)) {
                    tasksOnDate.add(e);
                }
            }
        });
        return getListAsStringFromList(tasksOnDate,
                "There are no tasks on this date!");
    }

    /**
     * Returns the String representing list of tasks with the keyword.
     *
     * @param keyword The specified keyword.
     * @return String representing the list of tasks.
     */
    public String getTaskWithKeyword(String keyword) {
        List<Task> tasksWithKeyword = list.stream().filter(
            task -> {
                String description = task.getDescription();
                return description.contains(keyword);
            }).collect(Collectors.toList());
        return getListAsStringFromList(tasksWithKeyword,
                "No tasks with " + keyword + " was found");
    }
}
