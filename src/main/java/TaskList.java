import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks, and provide methods for user to modify this list of tasks.
 */
public class TaskList {
    List<Task> listOfTasks;

    /**
     * Initializes an empty list of tasks.
     */
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Initializes the list of tasks with a pre-existing list.
     * @param listOfTasks The pre-existing list of tasks.
     */
    public TaskList(List<Task> listOfTasks) {
        this.listOfTasks = listOfTasks;
    }

    /**
     * Returns the list of tasks as a List.
     * @return List of tasks.
     */
    public List<Task> getListOfTasks() {
        return this.listOfTasks;
    }

    /**
     * Returns the deadlines and events filtered by a particular date.
     * @param date The date used to filter the tasks.
     * @return List of deadlines and events occurring on the date.
     */
    public List<Task> getListOfTasks(LocalDate date) {
        return this.listOfTasks.stream().filter(task -> (task instanceof Event && ((Event) task).isOnDate(date))
                || (task instanceof Deadline && ((Deadline) task).isOnDate(date))).collect(Collectors.toList());
    }

    /**
     * Returns list of tasks which description contains the keyword.
     * @param keyword The keyword to be used to filter the tasks.
     * @return The list of tasks.
     */
    public List<Task> getListOfTasks(String keyword) {
        return this.listOfTasks.stream().filter(
                task -> task.getDescription().contains(keyword)).collect(Collectors.toList());
    }

    /**
     * Marks the task at that particular index as done and returns that task.
     * @param idx The index to identify the task to be marked as done.
     * @return The task that has been marked as done.
     * @throws DukeException Throws DukeException when the index given is not valid.
     */
    public Task markTaskDone(int idx) throws DukeException {
        if (idx < 0 || idx >= listOfTasks.size()) {
            throw new DukeException("The task cannot be found.");
        }
        Task task = listOfTasks.get(idx);
        task.markAsDone();
        return task;
    }

    /**
     * Delete the task at that particular index and returns that task.
     * @param idx The index to identify the task to be deleted.
     * @return The task that has been deleted.
     * @throws DukeException Throws DukeException when the index given is not valid.
     */
    public Task deleteTask(int idx) throws DukeException {
        if (idx < 0 || idx >= listOfTasks.size()) {
            throw new DukeException("The task cannot be found.");
        }
        Task task = listOfTasks.get(idx);
        listOfTasks.remove(idx);
        return task;
    }

    /**
     * Adds a task to the list.
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.listOfTasks.add(task);
    }

    /**
     * Returns the current size of the task list.
     * @return Size of the task list.
     */
    public int size() {
        return this.listOfTasks.size();
    }
}
