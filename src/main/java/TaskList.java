import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    TaskList() {
        list = new ArrayList<>();
    }

    TaskList(List<Task> list) {
        this.list = list;
    }

    // after a task is done, the output message will be capture in duke class
    public void addTask(Task task) throws IOException {
        list.add(task);
    }

    public List<Task> getList() {
        return this.list;
    }

}
