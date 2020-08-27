import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> innerList;
    
    public TaskList() {
        innerList = new ArrayList<>();
    }
    
    public void addTask(Task newTask) {
        innerList.add(newTask);
    }
    
    public void deleteTask(int indexOfTaskToDelete) {
        innerList.remove(indexOfTaskToDelete - 1);
    }
    
}
