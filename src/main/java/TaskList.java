import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a task list which keeps track of the list of tasks.
 * It supports operations to manipulate the task list such as adding tasks,
 * deleting tasks, changing tasks to completed and getting specific tasks from the list.
 */
public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a task to the task list.
     * @param tsk the task to be added to the list.
     */
    public void addTask(Task tsk) {
        taskList.add(tsk);
    }

    /**
     * Deletes a task from the task list.
     * @param taskNum The task number to be deleted from the list.
     * @return The task that is deleted.
     * @throws InvalidTaskNumber If the task number is out of range.
     */
    public Task deleteTask(int taskNum) throws InvalidTaskNumber {
        if (taskNum < 1 || taskNum > taskList.size()) {
            throw new InvalidTaskNumber();
        } else {
            Task tsk = taskList.get(taskNum - 1);
            taskList.remove(taskNum - 1);
            return tsk;
        }
    }

    /**
     * Returns a list of all the tasks.
     * @return A list of all tasks.
     */
    public List<Task> getAllTasks() {
        return taskList;
    }

    /**
     * Marks the specified task with the number as completed and
     * returns the completed task.
     * @param taskNum The task number to be marked as done.
     * @return The completed task.
     * @throws InvalidTaskNumber If the task number is out of range.
     */
    public Task markDone(int taskNum) throws InvalidTaskNumber {
        if (taskNum < 1 || taskNum > taskList.size()) {
            throw new InvalidTaskNumber();
        } else {
            Task tsk = taskList.get(taskNum - 1);
            tsk.markAsDone();
            return tsk;
        }
    }

    /**
     * Returns a list of tasks with the specified date.
     * @param date The date of the task to find.
     * @return A list of task with the specified date.
     */
    public List<Task> getSameDateTasks(String date) {
        List<Task> listOfTasks = new ArrayList<>();
        LocalDate eventDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        for (Task task : taskList) {
            LocalDate dateInTask = task.getDate().orElse(null);
            if (eventDate.equals(dateInTask)) {
                listOfTasks.add(task);
            }
        }
        return listOfTasks;
    }

    /**
     * Returns the number of tasks in the list.
     * @return The total number of tasks in list.
     */
    public int getNumTasks() {
        return taskList.size();
    }

    @Override
    public String toString() {
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            content.append(taskList.get(i));
            content.append(System.lineSeparator());
        }
        return content.toString();
    }
}
