import java.util.ArrayList;
import java.util.List;

/**
 * represents a list of tasks
 */
public class TaskList {
    private List<Task> toDoLst;

    TaskList() {
        toDoLst = new ArrayList<>();
    }

    public List<Task> getToDoLst() {
        return toDoLst;
    }

    public boolean contains(Task task) {
        return toDoLst.contains(task);
    }

    public int getToDoLstSize() {
        return toDoLst.size();
    }

    public void addToDoItem(Task task) {
        toDoLst.add(task);
    }

    public Task removeToDoItem(int i) {
        Task deletedTask = toDoLst.remove(i);

        return deletedTask;
    }

    public List<Task> searchToDoItems(String searchTerm) {
        List<Task> result = new ArrayList<Task>();

        for (Task task: toDoLst) {
            String taskTodo = task.getTodo();

            if (taskTodo.matches(String.format(".*?\\b%s\\b.*?", searchTerm))) {
                result.add(task);
            }
        }

        return result;
    }
}
