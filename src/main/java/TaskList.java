import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> tl) {
        this.taskList = tl;
    }

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public void add(Task t) {
        taskList.add(t);
    }

    public Task delete(int i) {
        return taskList.remove(i);
    }

    public void markDone(int i) {
        taskList.get(i).markDone();
    }

    public Task getTask(int i) {
        return taskList.get(i);
    }

    public int getNumOfTasks() {
        return taskList.size();
    }
}
