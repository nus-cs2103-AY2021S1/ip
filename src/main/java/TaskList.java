import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Represents a task list.
 *
 * @author Siqi
 * @version 1.1
 * @since 2020-09-08
 */
public class TaskList {
    /**
     * The list of tasks.
     */
    private ArrayList<Task> taskList;

    /**
     * A TaskList constructor.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Gives the size of the task list.
     *
     * @return The size of task list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns the task at the specified index in the list.
     *
     * @param index The index of the task to be returned.
     * @return      The task at the specified index in the list.
     */
    public Task get(final int index) {
        return taskList.get(index);
    }

    /**
     * Adds a task to the list.
     *
     * @param task  Task to be added to list.
     */
    public void add(final Task task) {
        taskList.add(task);
    }

    /**
     * Marks the task at the specified index of the list as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public String markTaskAsDone(final int index) {
        Task newTask = this.taskList.get(index - 1).markAsDone();
        this.taskList.set(index - 1, newTask);
        return Ui.printMarkAsDone(newTask);
    }

    /**
     * Deletes the task at the index in the list.
     *
     * @param index The index of the task to be deleted.
     */
    public String deleteTask(final int index) {
        Task removed = this.taskList.get(index - 1);
        this.taskList.remove(index - 1);
        return Ui.printRemoveTask(removed, this.taskList);
    }

    /**
     * Adds a todo task to the list.
     *
     * @param input     This contains information about task to be added.
     * @return          String to be printed.
     */
    public String addTodo(final String input) {
        String desc = input.substring(4).trim();
        Task newTask = new Todo(desc);
        this.taskList.add(newTask);
        return Ui.printTaskAdded(newTask, taskList);
    }

    /**
     * Adds a deadline task (with date and time) to the list.
     *
     * @param desc          The description of the deadline task.
     * @param dateObject    The date associated with the deadline task.
     * @param timeString    The time associated with the deadline task.
     * @return              The string to be printed.
     */
    public String addDeadline(final String desc, final LocalDate dateObject,
                            final String timeString) {
        Task newTask = new Deadline(desc, dateObject, timeString);
        this.taskList.add(newTask);
        return Ui.printTaskAdded(newTask, this.taskList);
    }

    /**
     * Adds a deadline task (with date only) to the list.
     *
     * @param desc          The description of the deadline task.
     * @param dateObject    The date associated with the deadline task.
     * @return String to be printed.
     */
    public String addDeadline(final String desc, final LocalDate dateObject) {
        Task newTask = new Deadline(desc, dateObject);
        this.taskList.add(newTask);
        return Ui.printTaskAdded(newTask, this.taskList);
    }

    /**
     * Adds an event task (with date and time) to the list.
     *
     * @param desc          The description of the event task.
     * @param dateObject    The date associated with the event task.
     * @param timeString    The time associated with the deadline task.
     * @return String to be printed.
     */
    public String addEvent(final String desc, final LocalDate dateObject,
                         final String timeString) {
        Task newTask = new Event(desc.trim(), dateObject, timeString);
        this.taskList.add(newTask);
        return Ui.printTaskAdded(newTask, this.taskList);
    }

    /**
     * Adds an event task (with date only) in the list.
     *
     * @param desc          The description of the event task.
     * @param dateObject    The date associated with the event task.
     * @return String to be printed.
     */
    public String addEvent(final String desc, final LocalDate dateObject) {
        Task newTask = new Event(desc, dateObject);
        this.taskList.add(newTask);
        return Ui.printTaskAdded(newTask, this.taskList);
    }

    /**
     * Finds tasks that match the query.
     *
     * @param input The string containing query.
     * @return      The task list containing tasks that match query.
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
