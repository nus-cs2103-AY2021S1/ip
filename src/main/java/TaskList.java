import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public void showTasks() {
        int counter = 1;
        for (Task t : list) {
            System.out.println("        " + counter +  "." + t.toString());
            counter++;
        }
    }

    public void add(Task task) {
        list.add(task);
    }

    public Task delete(int num) {
        return list.remove(num);
    }

    public int size() {
        return list.size();
    }

    public Task get(int i) {
        return list.get(i);
    }
}
