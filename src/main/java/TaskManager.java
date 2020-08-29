import java.util.ArrayList;

/**
 * Manages a list of tasks for the user.
 */
public class TaskManager {

    public final ArrayList<Task> tasksList;

    public TaskManager() {
        tasksList = new ArrayList<>();
    }

    public Task getTask(int taskNum) {
        return tasksList.get(taskNum);
    }

    /**
     * Adds a task to the list.
     * @param task task to be added.
     */
    public void addTask(Task task) {
        tasksList.add(task);
    }

    public void setTaskDone(int taskNum) {
        tasksList.get(taskNum - 1).setDone();
    }

    public int getNumTasks() {
        return tasksList.size();
    }

    public ArrayList<Task> getTasksList() {
        return tasksList;
    }

    /**
     * Removes a task from the list.
     * @param taskNum number of tasks in the list.
     */
    public void removeTask(int taskNum) {
        tasksList.remove(taskNum - 1);
    }

    @Override
    public String toString() {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasksList.size(); i++) {
            result = result + (i+1) + ". " + tasksList.get(i) + "\n";
        }
        return result;
    }
}
