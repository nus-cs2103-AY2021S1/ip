import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public int getSize() {
        return this.tasks.size();
    }

    public Task getTask(int taskNum) {
        return this.tasks.get(taskNum - 1);
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public void deleteTask(int taskNum) {
        this.tasks.remove(taskNum - 1);
    }

    public void printList() {
        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            System.out.println(num + ". " + tasks.get(i));
        }
    }

    public TaskList findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            Task t = this.tasks.get(i);
            if (t.toString().contains(keyword)) {
                foundTasks.add(t);
            }
        }

        return new TaskList(foundTasks);
    }
}
