import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> innerList;
    
    public TaskList() {
        innerList = new ArrayList<>();
    }
    
    public TaskList(ArrayList<Task> tasks) {
        innerList = tasks;
    }
    
    public void add(Task newTask) {
        innerList.add(newTask);
    }
    
    public void delete(int indexOfTaskToDelete) {
        innerList.remove(indexOfTaskToDelete - 1);
    }
    
    public int size() {
        return innerList.size();
    }
    
    public Task getTask(int i) {
        assert i >= 1 : "Trying to get task with invalid task index";
        return innerList.get(i - 1);
    }
    
    public boolean isEmpty() {
        return innerList.isEmpty();
    }
    
}
