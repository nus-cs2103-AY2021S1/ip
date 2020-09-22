package duke;
import java.util.ArrayList;

/**
 * Creates a task list which contains a arraylist and a list of operations can
 * be performed on it. The javadoc for methods in this class are omitted.
 */
public class TaskList {
    protected ArrayList<Task> list;
    protected ArrayList<Task> lastList;

    protected TaskList(ArrayList<Task> list) {
        this.list = list;
        this.lastList = list;
    }
    
    protected ArrayList<Task> getList() {
        return this.list;
    }
    
    @SuppressWarnings("unchecked")
    protected void updateLastList() {
        this.lastList = (ArrayList<Task>) this.list.clone();
    }
    
    @SuppressWarnings("unchecked")
    protected void updateList() {
        this.list = (ArrayList<Task>) this.lastList.clone();
    }

    protected void add(Task task) {
        this.list.add(task);
    }
    
    protected Task get(int number) {
        return this.list.get(number);
    }

    protected void set(int number, Task task) {
        this.list.set(number, task);
    }
    
    protected void remove(int number) {
        this.list.remove(number);
    }
}
