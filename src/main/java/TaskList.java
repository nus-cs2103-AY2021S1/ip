import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private final List<Task> list = new ArrayList<>();

    protected void addTask(Task task) {
        list.add(task);
        System.out.println("added: " + task);
    }

    @Override
    public String toString() {
        String message = "";
        int count = 1;
        for (Task task : list) {
            message += String.format("%d. %s", count++, task);
        }
        message += "\n";
        return message;
    }
}
