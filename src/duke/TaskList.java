package duke;
import java.util.ArrayList;
public class TaskList {
    ArrayList<Task> arr;
    TaskList(ArrayList<Task> arr) {
        this.arr = arr;
    }

    /**
     * Adds the task to arr.
     * @param t task that is being added.
     */
    public void addTask(Task t) {
        arr.add(t);
    }

    /**
     * Removes the task to arr.
     * @param t task that is being added.
     */
    public void removeTask(Task t) {
        arr.remove(t);
    }
}
