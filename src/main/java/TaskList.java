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

    public Task getTask(int index){
        return this.taskList.get(index - 1);
    }

    public void markDone(int index){
        this.taskList.get(index - 1).markDone();
    }

    public void addTask(Task task){
        this.taskList.add(task);
    }

    public void deleteTask(int index){
        this.taskList.remove(index - 1);
    }

    public int getSize(){
        return this.taskList.size();
    }

}
