import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    TaskList() {
        this.tasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public int getNumOfTasks() {
        return this.tasks.size();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public void remove(int i) {
        this.tasks.remove(i);
    }

    public void add(Task t) {
        this.tasks.add(t);
    }

    public String displayTasks() {
        String output = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            output += (i + 1) + ". " + this.tasks.get(i).toString() + "\n";
        }
        return output;
    }
}
