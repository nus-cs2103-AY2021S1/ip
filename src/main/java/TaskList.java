import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;
    private int totalTasks;

    public TaskList(ArrayList<Task> tasksList) {
        this.tasks = tasksList;
        this.totalTasks = tasksList.size();
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
        this.totalTasks = 0;
    }

    public void add(Task task) {
        tasks.add(task);
        totalTasks++;
    }

    public void delete(int taskNumber) {
        tasks.remove(taskNumber - 1);
        totalTasks--;
    }

    public void markTaskAsDone(int taskNumber) {
        tasks.get(taskNumber - 1).markAsDone();
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    public int getTotalTasks() {
        return this.totalTasks;
    }

    public ArrayList<Task> getTasksList() {
        return this.tasks;
    }

    public void listTasks() {
        for (int i = 1; i <= totalTasks; i++) {
            System.out.println(i + "." + tasks.get(i - 1));
        }
    }
}
