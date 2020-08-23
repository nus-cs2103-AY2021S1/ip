import java.util.ArrayList;
import java.util.List;

/**
 * TaskList is responsible to store the array of lists temporarily,
 * and able to add new task into the list.
 */
public class TaskList {

    public static List<Task> taskList;

    /**
     * Constructor of tasklist.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Marks task item as done.
     *
     * @param index number of item in the list.
     */
    public void markDone(int index) {
        taskList.get(index).markAsDone();
    }

    /**
     * Adds task into the list.
     *
     * @param task task to be added into list.
     */
    public void add(Task task) {
        taskList.add(task);
    }

}
