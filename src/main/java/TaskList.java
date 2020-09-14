import java.util.ArrayList;
import java.util.List;

/**
 * Represents a task list contains the task list e.g.,
 * it has operations to add/delete tasks in the list
 */
public class TaskList {
    private List<Task> lst = new ArrayList<>();
    public static int numberOfDoneTasks = 0;
    public List<Task> getLst() {
        return lst;
    }

    public void add(Task task) {
        lst.add(task);
    }

    public void addOfType(String message, TaskType taskType) {
        switch (taskType) {
        case T:
            lst.add(new Todo(message));
            break;
        case D:
            lst.add(new Deadline(message));
            break;
        case E:
            lst.add(new Event(message));
            break;
        }
    }

    public int size() {
        return lst.size();
    }

    public Task get(int i) {
        return lst.get(i);
    }

    public void delete(int i) {
        lst.remove(i);
    }
}
