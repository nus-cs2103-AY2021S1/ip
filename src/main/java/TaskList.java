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
}
