import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskList implements Iterable<Task> {

    protected List<Task> listOfTasks;

    public TaskList() {
        listOfTasks = new ArrayList<>();
    }

    public void add(Task task) {
        listOfTasks.add(task);
    }

    public Task get(int index) {
        return listOfTasks.get(index - 1);
    }

    public Task remove(int index) {
        return listOfTasks.remove(index - 1);
    }

    public Iterator<Task> iterator() {
        return listOfTasks.iterator();
    }

    public int size() {
        return listOfTasks.size();
    }


}
