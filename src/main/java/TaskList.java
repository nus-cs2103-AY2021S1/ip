import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * TaskList class stores the Tasks used in the application when it is running.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor that creates a new TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to TaskList.
     * @param task the Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task in TaskList.
     * @param index the index of the Task to be deleted.
     */
    public void deleteTask(int index) {
        tasks.remove(index - 1);
    }

    /**
     * Marks a tasks in the TaskList as done.
     * @param index the index of the Task to be marked as done.
     */
    public void markDone(int index) {
        tasks.get(index - 1).markDone();
    }

    /**
     * Retrieves a Task from the TaskList.
     * @param index the index of the Task to be retrieved.
     * @return the Task retrieved from the TaskList.
     */
    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Creates and returns a new TaskList with tasks that match the user date input.
     * @param date date of the Tasks.
     * @return a new TaskList with tasks that match the user date input.
     */
    public TaskList getTasksOnDate(String date) {
        LocalDate userInputDate = LocalDate.parse(date,
                DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        TaskList tasksOnDate = new TaskList();
        for(Task task: tasks) {
            if(task instanceof TimedTask) {
                LocalDateTime taskDateTime = ((TimedTask) task).getDateTime();
                LocalDate taskDate = taskDateTime.toLocalDate();
                if(taskDate.equals(userInputDate)) {
                    tasksOnDate.addTask(task);
                }
            }
        }
        return tasksOnDate;
        
    }

    /**
     * Returns the number of Tasks in the TaskList.
     * @return the number of Tasks in the TaskList.
     */
    public int size() {
        return tasks.size();
    }
}
