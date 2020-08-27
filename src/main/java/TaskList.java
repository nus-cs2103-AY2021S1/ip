import java.util.ArrayList;
import java.util.List;

public class TaskList{

    private final List<Task> taskList;

    /**
     * Constructor for a TaskList object
     */
    public TaskList(){
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for a TaskList object
     * @param tasks
     */
    public TaskList(List<Task> tasks){
        this.taskList = tasks;
    }

    /**
     * method to get the list
     * @return returns the List of tasks
     */
    public List<Task> getList(){
        return this.taskList;
    }

    /**
     * method to get a Task from the list
     * @param index index of the task to be fetched
     * @return returns the required task
     */
    public Task getTask(int index){
        return this.taskList.get(index - 1);
    }

    /**
     * method for marking a task as done
     * @param index index of task to be marked done
     */
    public void markDone(int index){
        this.taskList.get(index - 1).markDone();
    }

    /**
     * method to add a task to the list
     * @param task task to be added
     */
    public void addTask(Task task){
        this.taskList.add(task);
    }
  
    /**
     * method to delete a task
     * @param index index of the task to be deleted
     */
    public void deleteTask(int index){
        this.taskList.remove(index - 1);
    }

    /**
     * method to get size
     * @return returns the size of the task list
     */
    public int getSize(){
        return this.taskList.size();
    }

}
