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
        int counter = 1;
        for (Task task : list) {
            ls.append(counter).append(".").append(task).append("\n");
            counter++;
        }
        return ls.toString();
    }
}
