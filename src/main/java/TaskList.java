import java.util.ArrayList;

public class TaskList {
    private ArrayList<String> lines;
    private int numberOfItems;

    public TaskList(ArrayList<String> lines) {
        this.lines = lines;
        this.numberOfItems = lines.size();
    }

    public void addTask(String task) {
        if (numberOfItems < 100) {
            lines.add(task);
            numberOfItems += 1;
        }
    }

    public void removeTask(int position) {
        lines.remove(position);
        numberOfItems -= 1;
    }

    public void updateTask(String task, int position) {
        lines.set(position, task);
    }

    public ArrayList<String> getList() {
        return lines;
    }

    public String getTask(int position) {
        return lines.get(position);
    }

    public int getNumberOfItems() {
        return numberOfItems;
    }
}
