import java.util.ArrayList;

public class TaskList {

    public ArrayList<Task> lst;

    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    public void markTaskAsDone(int num) {
        lst.get(num).markAsDone();
    }

    public void addTask(Task task) {
        lst.add(task);
    }

    public void deleteTask(int num) {
        lst.remove(num);
    }
}
