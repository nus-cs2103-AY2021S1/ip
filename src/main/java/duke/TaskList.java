package duke;
import java.util.ArrayList;
public class TaskList {
    private final ArrayList<Task> arr;
    TaskList(ArrayList<Task> arr) {
        this.arr = arr;
    }

    /**
     * Getter fot the arr.
     * @return arr
     */
    public ArrayList<Task> getArr() {
        return this.arr;
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
