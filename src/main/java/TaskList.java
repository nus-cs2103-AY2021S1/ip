import java.util.ArrayList;

public class TaskList {

    private int numTasks;
    private ArrayList<Task> list;

    public TaskList() {
        this.numTasks = 1;
        this.list = new ArrayList<>();
    }

    public void addItem(Task item) {
        numTasks++;
        list.add(item);
    }

    public int getNumTasks() {
        return numTasks;
    }
    public ArrayList<Task> getTasks() {
        return list;
    }

    public String toString() {
        StringBuilder ls = new StringBuilder();
        for (Task task : list) {
            ls.append(task).append("\n");
        }
        return ls.toString();
    }
}
