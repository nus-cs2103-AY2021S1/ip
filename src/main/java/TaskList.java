import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a TaskList object.
 * Stores the List of Tasks.
 */
public class TaskList {
    private final String IMPOSSIBLE_DATE = "2000-01-01";
    private final static String DATE_FORMAT = "MMM d yyyy";

    private List<Task> tasks;

    /**
     * Creates a TaskList object to store tasks
     *
     * @param tasks is the list of Task objects.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the list of Tasks.
     *
     * @return current list of Tasks stored.
     */
    public List<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Returns the number of Tasks stored.
     *
     * @return current number of Tasks stored.
     */
    public int getTaskNum() {
        return this.tasks.size();
    }

    /**
     * A command to store a Task object.
     *
     * @param newTask is the new Task to be stored.
     */
    public void store(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * A command to remove a Task object from current list.
     * Returns a Task object that is removed.
     *
     * @param index specifies the position of Task in the list.
     */
    public Task remove(int index) {
        Task removedTask = tasks.get(index);
        tasks.remove(index);
        return removedTask;
    }

    /**
     * A command to mark a Task object from current list as done.
     *
     * @param index identifies the Task object to be marked as done.
     */
    public Task markDone(int index) {
        Task doneTask = tasks.get(index);
        doneTask.markAsDone();
        return doneTask;
    }

    /**
     * A sub parser that formats the Task from data file Date to LocalDate
     *
     * @param currentTask identifies the Task object.
     * @param taskType identifies which sub task is the Task object.
     *
     * @return a LocalDate object from the Task.
     */
    public LocalDate taskDateFormatter(Task currentTask, String taskType) {
        if (taskType.equals(Task.EVENT_TASK)) {
            Event currentEventTask = (Event)currentTask;
            String formattedTime = currentEventTask.getFormattedTime();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            return LocalDate.parse(formattedTime, dateFormatter);

        } else if (taskType.equals(Task.DEADLINE_TASK)) {
            Deadline currentDeadlineTask = (Deadline)currentTask;
            String formattedTime = currentDeadlineTask.getFormattedTime();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
            return LocalDate.parse(formattedTime, dateFormatter);

        } else {
            return LocalDate.parse(IMPOSSIBLE_DATE);
        }
    }

    /**
     * Returns a reminder message depending on whether there are urgent tasks or not.
     *
     * @param reminderTasks a list of tasks to be printed as reminders.
     *
     * @return a String output of reminder tasks.
     */
    public String getReminderString(List<Task> reminderTasks) {
        if (reminderTasks.size() < 1) {
            return "***Reminder:\n"
                    + "There are no urgent tasks to be completed.\n"
                    + "You can take a break! :)";

        } else {
            String output = "***Reminder:\n"
                    + "Here are your tasks to be completed within 3 days:\n";
            int partialSize = reminderTasks.size();
            int index = 1;
            for (int i = 0; i < partialSize; i++) {
                output = output + "  " + index + "." + reminderTasks.get(i) + "\n";
                index++;
            }

            return output;
        }
    }

    /**
     * Returns a reminder message depending on whether there are urgent tasks or not.
     *
     * @return a String representation of reminder message.
     */
    public String getUrgentTasks() {
        List<Task> tasks = this.getTasks();
        List<Task> filteredTasks = new ArrayList<>();
        int size = tasks.size();
        LocalDate now = LocalDate.now().plusDays(3);

        for (int i = 0; i < size; i++) {
            Task currentTask = tasks.get(i);
            String taskType = currentTask.getType();
            LocalDate taskTime;
            taskTime = taskDateFormatter(currentTask, taskType);

            if (!(currentTask.getType().equals(Task.TODO_TASK))
                    && currentTask.getCompletionStatus() == false && now.isAfter(taskTime)) {
                filteredTasks.add(currentTask);
            }
        }

        for (int i = 0; i < size; i++) {
            Task currentTask = tasks.get(i);
            if (currentTask.getCompletionStatus() == false && currentTask.getType().equals(Task.TODO_TASK)) {
                filteredTasks.add(currentTask);
            }
        }

        return getReminderString(filteredTasks);
    }
}
