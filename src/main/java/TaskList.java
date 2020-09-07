import java.io.Serializable;
import java.util.ArrayList;

/**
 * stores the tasks in a list
 */
public class TaskList implements Serializable {
    private ArrayList<Task> taskList = new ArrayList<>();
    private final long serialVersionUID = 1089785654636785606L;

    /**
     * prints the tasks in the taskList
     *
     * @return string format of the task list
     */
    public String toString() {
        int counter = 1;
        String taskListString = "";
        for (Task task : taskList) {
            taskListString += counter + ". " + task + "\n";
            counter++;
        }
        return taskListString;
    }

    /**
     * returns the total number of tasks in the task list
     *
     * @return the number of tasks
     */
    public int getTaskLength() {
        return taskList.size();
    }

    /**
     * getter for the task of a particular number
     *
     * @param index of the task
     * @return the task that user wants to retrieve (based on task number)
     */
    public Task getTask(int index) {
        return taskList.get(index - 1);
    }

    /**
     * removes the tasks
     *
     * @param task
     */
    public void removeTask(Task task) {
        taskList.remove(task);
    }

    /**
     * adds the tasks
     *
     * @param task
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * shows the task list of tasks with keywords
     *
     * @param keyword
     * @return task list of tasks with keywords
     */
    public TaskList getTasksWithKeyWords(String keyword) {
        TaskList newTaskList = new TaskList();
        for (Task task : taskList) {
            if (task.getDescription().contains(keyword)) {
                newTaskList.addTask(task);
            }
        }
        return newTaskList;
    }
}


