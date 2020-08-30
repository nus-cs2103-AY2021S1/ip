import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public int size() {
        return this.taskList.size();
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void set(int index, Task currentTask) {
        this.taskList.set(index, currentTask);
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public Task remove(int index) {
        Task deletedTask = this.taskList.get(index);
        this.taskList.remove(index);
        return deletedTask;
    }

    public void print() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            String stringCount = String.valueOf(i + 1);
            System.out.println(stringCount + ". " + taskList.get(i));
        }
    }

    public Task setDoneTask(int index) {
        Task task = this.taskList.get(index);
        task.markDone();
        this.taskList.set(index, task);
        return task;
    }

}
