import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private final List<Task> list = new ArrayList<>();

    protected void addTask(Task task) {
        list.add(task);
        System.out.println("added: " + task + "\n");
    }

    protected void markTaskDone(int listIndex) {
        Task task = list.get(listIndex - 1);
        task.markDone();
        System.out.println(String.format("Hurray! %s is now done.", task));
        System.out.println(String.format("[\u2713] %s\n", task));
    }

    @Override
    public String toString() {
        String message = "";
        int count = 1;
        for (Task task : list) {
            message += String.format("%d.[%s] %s\n"
                    , count++
                    , task.isDone() ? "\u2713" : "\u2717"
                    , task);
        }
        return message;
    }
}
