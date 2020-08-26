import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> taskList;
    
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    
    public TaskList() {
        taskList = new ArrayList<>();
    }
    
    public int taskListLength() {
        return taskList.size();
    }
    
    public Task addTask(Task task) {
        taskList.add(task);
        return task;
    }
    
    public Task deleteTask(int index) {
        Task task = taskList.get(index);
        taskList.remove(index);
        return task;
    }
    
    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}
