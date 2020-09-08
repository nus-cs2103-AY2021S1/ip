import java.util.ArrayList;
import java.util.List;

/**
 * TaskList class that stores the Tasks in the Duke object.
 */
public class TaskList {
    public List<Task> taskList;

    /**
     * Overloaded constructor that creates an empty TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Overloaded constructor that takes in a List of Takes and creates a TaskList object
     * and adds Tasks in the List.
     * @param taskList
     */
    public TaskList(List<Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    public static TaskList parse(List<Task> tasks) {
        return new TaskList(tasks);
    }

    /**
     * Adds a Task to the TaskList.
     * @param task task the Task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Deletes a Task in the TaskList.
     * @param num index of the Task to be deleted.
     */
    public void deleteTask(int num) {
        taskList.remove(num - 1);
    }

    public Task getTask(int num) {
        return this.taskList.get(num - 1);
    }

    public int size() {
        return this.taskList.size();
    }
}
