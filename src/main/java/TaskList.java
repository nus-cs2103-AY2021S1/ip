import java.util.Collection;
import java.util.LinkedList;

public class TaskList extends LinkedList<Task> {
    TaskList(Collection<Task> tasks) {
        super(tasks);
    }

    void delete(int id) {
        remove(id);
    }
    
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < size(); i++) {
            result += String.format("      %s. %s\n", i + 1, this.get(i));
        }
        return result;
    }
}
