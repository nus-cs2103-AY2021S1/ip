import java.util.ArrayList;

/**
 * Manages a list of tasks for the user.
 */
public class TaskManager {

    public final ArrayList<Task> taskList;

    public TaskManager() {
        this.taskList = new ArrayList<>();
    }

    public Task getTask(int taskNum) {
        return this.taskList.get(taskNum);
    }

    /**
     * Adds a task to the list.
     * @param task task to be added.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    public void setTaskDone(int taskNum) {
        taskList.get(taskNum - 1).setDone();
    }

    public int getNumTasks() {
        return taskList.size();
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskPriority(int taskNum, int priority) {
        taskList.get(taskNum - 1).setPriority(priority);
    }

    /**
     * Removes a task from the list.
     * @param taskNum number of tasks in the list.
     */
    public void removeTask(int taskNum) {
        taskList.remove(taskNum - 1);
    }

    @Override
    public String toString() {
        if (this.getTaskList().size() == 0) {
            return "You have nothing to do!";
        } else {
            String result = "Here are the tasks in your list:\n";
            for (int i = 0; i < taskList.size(); i++) {
                result = result + (i + 1) + ". " + taskList.get(i) + "\n";
            }
            return result;
        }
    }
}
