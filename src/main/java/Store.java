import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Task> list;

    public Store() {
        this.list = new ArrayList<>();
    }

    public void addItem(Task item) {
        this.list.add(item);
    }

    public int size() {
        return this.list.size();
    }

    public void setDone(int index) {
        Task task = this.list.get(index);
        task.setDone(true);
    }

    public Task getItem(int index) {
        return this.list.get(index);
    }

    public List getList() {
        return this.list;
    }

    public Task remove(int index) {
        return this.list.remove(index);
    };
}
