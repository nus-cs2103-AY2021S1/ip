import java.util.ArrayList;

public class ToDoList {
    private final ArrayList<Task> list;

    public ToDoList() {
        list = new ArrayList<Task>();
    }

    public void addTask(String description) {
        Task task = new Task(description);
        list.add(task);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int index = 1;
        for (Task task : list) {
            sb.append(index).append(". ").append(task.toString()).append("\n ");
            index++;
        }
        return sb.delete(sb.length() - 2, sb.length() - 1).toString();
    }
}
