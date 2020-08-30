package duke;
import java.util.List;

public class TaskList {
    private List<Task> taskList;
    
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }
    
    public List<Task> getListOfTasks() {
        return this.taskList;
    }
    
    public int getTotalTask() {
        return taskList.size();
    }
    
    public void addTask(Task task) {
        this.taskList.add(task);
    }
    
    public void deleteTaskIndex(int index) {
        try {
            taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new TaskListError(2,index,getTotalTask());
        }
    }
    
    public Task get(int index) {
        try {
            return taskList.get(index);
        } catch (IndexOutOfBoundsException e){
            throw new TaskListError(1,index,getTotalTask());
        }
    }
}
