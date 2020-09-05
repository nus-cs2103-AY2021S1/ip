import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Class to represent all the tasks.
 * @author vanGoghhh
 */

public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructor for tasklist.
     *
     * @param taskList arraylist containing all the tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Mark a task as completed.
     *
     * @param completedTask task to be completed.
     */
    protected void markTaskDone(Task completedTask) {
        completedTask.markAsDone();
        int indexOfTask = this.taskList.indexOf(completedTask);
        this.taskList.get(indexOfTask).markAsDone();

    }

    /**
     * Adds a task into the tasklist.
     *
     * @param task the task to be added.
     */
    protected void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a task from the tasklist.
     *
     * @param task the task to be deleted.
     */
    protected void deleteTask(Task task) {
        this.taskList.remove(task);
    }

    /**
     * .
     * Gets the tasklist containing all the tasks.
     *
     * @return arraylist containing all the tasks.
     */
    protected ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Returns number of uncompleted tasks in the tasklist.
     *
     * @return number of uncompleted tasks.
     */
    protected int checkTasksLeft() {
        int index = 0;
        for (Task task : this.taskList) {
            if (!task.getStatus()) {
                index++;
            }
        }
        return index;
    }

    /**
     * Search and return tasks using keyword.
     *
     * @param keyWord word used to search.
     * @return Tasks with description matching the keyword.
     */
    protected ArrayList<Task> findTask(String keyWord) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : this.taskList) {
            if (task.description.equals(keyWord)) {
                foundTasks.add(task);
            }
        }
        return foundTasks;
    }

    protected ArrayList<Task> filterTask() {
        return this.taskList.stream().filter(task -> task instanceof Deadline ||
                task instanceof Event).collect(Collectors.toCollection(ArrayList::new));
    }
}
