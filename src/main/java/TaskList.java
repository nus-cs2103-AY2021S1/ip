import java.util.ArrayList;
import java.util.List;

public class TaskList{

    private final List<Task> taskList;

    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> tasks){
        this.taskList = tasks;
    }

    public List<Task> getList(){
        return this.taskList;
    }

    public Task getTask(int i){
        return this.taskList.get(i - 1);
    }

    public void markDone(int i){
        this.taskList.get(i - 1).markDone();
    }

    public void addTask(Task task){
        this.taskList.add(task);
    }

    public void deleteTask(int i){
        this.taskList.remove(i - 1);
    }

    public int getSize(){
        return this.taskList.size();
    }

}
