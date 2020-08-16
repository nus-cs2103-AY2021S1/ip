import java.util.ArrayList;

public class TaskList {

    private int numTasks;
    private ArrayList<String> list;

    public TaskList() {
        numTasks = 0;
        this.list = new ArrayList<>();
    }

    public void addItem(String item) {
        numTasks++;
        String added = numTasks + ". " + item;
        list.add(added);
    }

    public String toString() {
        String ls = "";
        for (String item : list) {
            ls += item + "\n";
        }
        return ls;
    }
}
