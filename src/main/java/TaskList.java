import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;
    private Ui ui;

    public TaskList(ArrayList<Task> taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public void add(Task task) {
        this.taskList.add(task);
        this.ui.showAddedToList(task);
    }

    public void load(Task task) {
        this.taskList.add(task);
    }

    public void delete(int i) {
        Task task = this.taskList.get(i - 1);
        this.taskList.remove(i - 1);
        this.ui.showDelete(task);
    }

    public void markDone(int i) {
        Task task = this.taskList.get(i - 1);
        task.markAsDone();
        this.ui.showMarkDone(task);
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public Task getTask(int i) {
        return this.taskList.get(i);
    }

    public int getTotalTask() {
        return this.taskList.size();
    }
}
