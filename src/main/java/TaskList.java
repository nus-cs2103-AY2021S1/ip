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
    ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public int size() {
        return taskList.size();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * This method marks the task at index index of the list as done.
     * @param index     The index of the task to be marked as done.
     */
    public void setDone(int index) {
        Task newTask = this.taskList.get(index - 1).markAsDone();
        this.taskList.set(index - 1, newTask);
        Ui.printMarkAsDone(newTask);
    }

    /**
     * This method deletes the task at the index in the list.
     * @param index     The index of the task to be deleted.
     */
    public void setDelete(int index) {
        Task removed = this.taskList.get(index - 1);
        this.taskList.remove(index - 1);
        Ui.printRemoveTask(removed, this.taskList);
    }

    /**
     * This method adds a to do task in the list
     * @param input     This string contains information about the task to be added.
     */
    public void setTodo(String input) {
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
    public void setDeadline(String description, LocalDate d1, String timeString) {
        Task newTask = new Deadline(description.trim(), d1, timeString);
        this.taskList.add(newTask);
        Ui.printTaskAdded(newTask, this.taskList);
    }

    /**
     * This method adds a deadline task in the list.
     * @param description   This is the description of the deadline task.
     * @param d1            This is the date associated with the deadline task.
     */
    public void setDeadline(String description, LocalDate d1) {
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
    public void setEvent(String description, LocalDate d2, String timeString) {
        Task newTask = new Event(description.trim(), d2, timeString);
        this.taskList.add(newTask);
        Ui.printTaskAdded(newTask, this.taskList);
    }

    /**
     * This method adds an event task in the list.
     * @param description   This is the description of the event task.
     * @param d2            This is the date associated with the event task.
     */
    public void setEvent(String description, LocalDate d2) {
        Task newTask = new Event(description.trim(), d2);
        this.taskList.add(newTask);
        Ui.printTaskAdded(newTask, this.taskList);
    }
}
