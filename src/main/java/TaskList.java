import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void remove(int i) {
        this.taskList.remove(i);
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int i) {
        return this.taskList.get(i);
    }

    public String toString() {
        String finalString = "";
        for (int i = 0; i < this.taskList.size(); i++) {
            finalString += this.taskList.get(i).toString() + "\n";
        }
        return finalString;
    }


}
