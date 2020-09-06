package duke;

import java.util.ArrayList;
import java.util.List;

import duke.exception.DukeInvalidUpdateException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;

/**
 * Contains a list of tasks.
 * Responsible for any task related operations.
 */
public class TaskList {

    /**
     * The list of tasks.
     */
    private List<Task> tasks;

    /**
     * Creates a new instance of a TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a new instance of a TaskList object with attributes defined
     * in the parameters.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Retrieves the task according to the index given.
     *
     * @param i Index of the task in the list.
     * @return Returns the task.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Retrieves the size of the task list.
     *
     * @return Returns the size.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves the task list.
     *
     * @return Returns the list of tasks.
     */
    public List<Task> getList() {
        return this.tasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a task in the task list as complete.
     *
     * @param taskNo Index of the task to be completed.
     * @return Returns the task.
     */
    public Task completeTask(int taskNo) {
        Task task = tasks.get(taskNo - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Removes a task in the task list.
     *
     * @param taskNo Index of the task to be removed.
     * @return Returns the removed task.
     */
    public Task deleteTask(int taskNo) {
        Task task = tasks.get(taskNo - 1);
        tasks.remove(task);
        return task;
    }

    /**
     * Edits a task's description in the task list.
     *
     * @param taskNo Index of the task to be edited.
     * @param description New description of the task.
     * @return Returns the edited task.
     */
    public Task updateTaskDesc(int taskNo, String description) {
        Task task = tasks.get(taskNo - 1);
        task.updateDesc(description);
        return task;
    }

    /**
     * Edits a task's date in the task list.
     *
     * @param taskNo Index of the task to be edited.
     * @param date New date of the task.
     * @return Returns the edited task.
     * @throws DukeInvalidUpdateException when attempting to update an invalid detail.
     */
    public Task updateTaskDate(int taskNo, String date) throws DukeInvalidUpdateException {
        Task task = tasks.get(taskNo - 1);
        if (task instanceof Event) {
            Event event = (Event) task;
            event.updateDate(date);
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            deadline.updateDate(date);
        } else {
            throw new DukeInvalidUpdateException();
        }
        return task;
    }



    /**
     * Finds the tasks related to keyword.
     *
     * @param keyword Keyword used to find the related tasks.
     * @return Returns a list of related tasks.
     */
    public List<Task> findTasks(String keyword) {
        List<Task> relatedTasks = new ArrayList<>();
        tasks.stream().filter(task -> task.getDescription().contains(keyword))
                .forEach(task -> relatedTasks.add(task));
        return relatedTasks;
    }

    /**
     * Retrieves a string describing the task size.
     *
     * @return Returns the string.
     */
    public String getTaskSizeMessage() {
        return "Now you have " + tasks.size() + " tasks in the list.";
    }
}
