import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println(">> Added the task:\n>> " + task + "\n>> You now have " + tasks.size() + " tasks to do!");
    }

    public void deleteTask(int idx) {
        Task task = tasks.get(idx);
        tasks.remove(idx);
        System.out.println(">> I've eradicated the task:\n>> " + task + "\n>> You now have " + tasks.size() + " tasks to do!");
    }

    public void completeTask(int idx) {
        tasks.get(idx).complete();
        System.out.println(">> Yay! The following task is marked as done:\n>> " + tasks.get(idx));
    }
}
