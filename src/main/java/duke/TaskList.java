package duke;
import java.util.List;

/**
 * Stores tasks in a list
 */

public class TaskList {
    private List<Task> taskList;

    /**
     * Constructor to initialize the tasks 
     * @param taskList
     */
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
