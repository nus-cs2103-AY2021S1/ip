import java.util.ArrayList;

public class TaskList {

    private int numTasks;
    private ArrayList<Task> list;

    public TaskList() {
        this.numTasks = 1;
        this.list = new ArrayList<>();
    }

    /**
     * Adds a task to the ArrayList of tasks
     * @param item The task to be added
     */
    public void addItem(Task item) {
        numTasks++;
        list.add(item);
    }

    public int size() {
        return list.size();
    }

    public int getNumTasks() {
        return numTasks;
    }
    public ArrayList<Task> getTasks() {
        return list;
    }

    public String toString() {
        StringBuilder ls = new StringBuilder();
        int counter = 1;
        for (Task task : list) {
            ls.append(counter == 1 ? "" : "\n").append(counter).append(".").append(task);
            counter++;
        }
        return ls.toString();
    }
}
