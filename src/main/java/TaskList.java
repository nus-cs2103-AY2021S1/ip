import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    ArrayList<Task> taskList;

    /**
     * Creates an empty list of task.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Creates a list of tasks.
     * @param taskList List of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets the task at a specific index from the list.
     * @param index Index of task item in the list.
     * @return
     */
    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    /**
     * Adds a task to the list.
     * @param task Task that needs to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a task at a specific index from the list.
     * @param index Index of task item in the list.
     */
    public void deleteTask(int index) {
        this.taskList.remove(index);
    }

    /**
     * Gets the size of list.
     * @return Size of list.
     */
    public int getSize() {
        return this.taskList.size();
    }

    /**
     * Gets the list of tasks.
     * @return List of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
    
    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < getSize(); i++) {
            if (i == getSize() - 1) {
                string += ((i + 1) + ". " + getTask(i).toString());
            } else {
                string += ((i + 1) + ". " + getTask(i).toString() + "\n");
            }
        }
        return string;
    }
    
}
