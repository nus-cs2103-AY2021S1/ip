import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a task list.
 *
 * @author Siqi
 * @version 1.0
 * @since 2020-08-25
 */
public class TaskList {
    /**
     * List of tasks.
     */
    private ArrayList<Task> taskList;

    /**
     * Tasklist constructor.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * This method gives the size of the task list.
     * @return The size of task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * This method returns Task at index position in list.
     * @param index The index of the task to be returned.
     * @return  The task at index position in the list.
     */
    public Task get(final int index) {
        return taskList.get(index);
    }

    /**
     * This method adds a task to the list.
     * @param task Task to be added to list.
     */
    public void add(final Task task) {
        taskList.add(task);
    }

    /**
     * This method marks the task at index index of the list as done.
     * @param index     The index of the task to be marked as done.
     */
    public void markTaskAsDone(final int index) {
        Task newTask = this.taskList.get(index - 1).markAsDone();
        this.taskList.set(index - 1, newTask);
        Ui.printMarkAsDone(newTask);
    }

    /**
     * This method deletes the task at the index in the list.
     * @param index     The index of the task to be deleted.
     */
    public void deleteTask(final int index) {
        Task removed = this.taskList.get(index - 1);
        this.taskList.remove(index - 1);
        Ui.printRemoveTask(removed, this.taskList);
    }

    /**
     * This method adds a to do task in the list.
     * @param input     This contains information about task to be added.
     */
    public void addTodo(final String input) {
        String description = input.substring(4);
        Task newTask = new Todo(description.trim());
        this.taskList.add(newTask);
        Ui.printTaskAdded(newTask, taskList);
    }

    /**
     * This method adds a deadline task in the list.
     * @param description   This is the description of the deadline task.
     * @param d1            This is the date associated with the deadline task.
     * @param timeString    This is the time associated with the deadline task.
     */
    public void addDeadline(final String description, final LocalDate d1,
                            final String timeString) {
        Task newTask = new Deadline(description.trim(), d1, timeString);
        this.taskList.add(newTask);
        Ui.printTaskAdded(newTask, this.taskList);
    }

    /**
     * This method adds a deadline task in the list.
     * @param description   This is the description of the deadline task.
     * @param d1            This is the date associated with the deadline task.
     */
    public void addDeadline(final String description, final LocalDate d1) {
        Task newTask = new Deadline(description.trim(), d1);
        this.taskList.add(newTask);
        Ui.printTaskAdded(newTask, this.taskList);
    }

    /**
     * This method adds an event task in the list.
     * @param description   This is the description of the event task.
     * @param d2            This is the date associated with the event task.
     * @param timeString    This is the time associated with the deadline task.
     */
    public void addEvent(final String description, final LocalDate d2,
                         final String timeString) {
        Task newTask = new Event(description.trim(), d2, timeString);
        this.taskList.add(newTask);
        Ui.printTaskAdded(newTask, this.taskList);
    }

    /**
     * This method adds an event task in the list.
     * @param description   This is the description of the event task.
     * @param d2            This is the date associated with the event task.
     */
    public void addEvent(final String description, final LocalDate d2) {
        Task newTask = new Event(description.trim(), d2);
        this.taskList.add(newTask);
        Ui.printTaskAdded(newTask, this.taskList);
    }

    /**
     * This method finds tasks that match the query.
     * @param input String containing query.
     * @return      Task list containing tasks that match query.
     */
    public TaskList findTasks(final String input) {
        TaskList result = new TaskList();
        for (int i = 0; i < this.taskList.size(); i++) {
            String taskDescription = this.taskList.get(i).getDescription();
            if (taskDescription.contains(input)) {
                result.add(this.taskList.get(i));
            }
        }
        return result;
    }
}
