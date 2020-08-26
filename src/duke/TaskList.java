package duke;
import java.util.ArrayList;
public class TaskList {
    private ArrayList<Task> arr;
    TaskList(ArrayList<Task> arr) {
        this.arr = arr;
    }

    public ArrayList<Task> getArr() {
        return this.arr;
    }

    public void addTask(Task t) {
        arr.add(t);
    }

    public void removeTask(Task t) {
        arr.remove(t);
    }
}
