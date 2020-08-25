import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> list = new ArrayList<Task>();

    public TaskList() {}

    public TaskList(List<Task> list) {
        this.list = list;
    }

    public List<Task> getList() { return list; }

    public void setList(List<Task> list) { this.list = list; }
}
