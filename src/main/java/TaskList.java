import java.util.List;

public class TaskList {
    List<Task> list;
    
    public TaskList(List<Task> list) {
        this.list = list;
    }
    
    public boolean isEmpty() {
        return this.list.isEmpty();
    }
    
    public int size() {
        return this.list.size();
    }
    
    public Task get(int index) {
        return this.list.get(index);
    }
    
    public void updateStorage(Storage storage) {
        storage.update(list);
    }
    
    public void add(Task task, Storage storage) {
        this.list.add(task);
        this.updateStorage(storage);
    }
    
    public Task remove(int index, Storage storage) {
        Task task = this.list.remove(index);
        this.updateStorage(storage);
        
        return task;
    }
}
