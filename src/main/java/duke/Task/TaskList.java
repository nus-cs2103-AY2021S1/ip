package duke.task;

import duke.exception.DukeException;

import java.util.ArrayList;

import static duke.ui.Ui.LINE_SEPARATOR;

/**
 * Represents a list of tasks.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Constructs a task list containing the elements of the specifies ArrayList of tasks,
     * in the order they are returned by the list's iterator.
     *
     * @param tasks The ArrayList of tasks whose tasks are to be placed in the task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Retrieves the requested <code>Task</code> Object from the list.
     *
     * @param taskId The 1-based index of the task to be fetched.
     * @return The requested <code>Task</code> object.
     * @throws DukeException If an invalid taskId is passed in.
     */
    public Task get(int taskId) throws DukeException {
        try {
            return tasks.get(taskId - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(String.format("task No.%d is not in your list. "
                    + "Please enter a valid task ID!", taskId));
        }
    }

    /**
     * Adds the <code>Task</code> Object from user input to the list.
     *
     * @param task The <code>Task</code> Object to be added to the task list.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Removes the requested <code>Task</code> Object from the list.
     *
     * @param taskId The 1-based index of the task to be fetched.
     * @throws DukeException If an invalid taskId is passed in.
     */
    public void remove(int taskId) throws DukeException {
        Task task = get(taskId);
        tasks.remove(task);
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns true if the task list contains no tasks.
     *
     * @return True if the task list contains no task.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Filters the task list that contains the given keyword and returns a new task list.
     *
     * @param keyword The keyword from user input to filter the task list.
     * @return The list of <code>Task</code> objects that contains the keyword.
     */
    public TaskList filter(String keyword) {
        ArrayList<Task> resultWithKeyword = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                resultWithKeyword.add(task);
            }
        }
        return new TaskList(resultWithKeyword);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i - 1);
            sb.append(String.format("%d.%s", i, task));
            if (i != tasks.size()) {
                sb.append(LINE_SEPARATOR);
            }
        }
        sb.append(LINE_SEPARATOR);
        return sb.toString();
    }
}
