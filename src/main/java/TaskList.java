import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Retrieves the ArrayList of Tasks attribute
     * @return returns the TaskList's ArrayList of Tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
