import java.util.ArrayList;

/**
 *  The TaskList class is responsible for storing the various Tasks in a list.
 */
public class TaskList {
    private ArrayList<Task> taskList;
    
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    
    public ArrayList<Task> getTaskList() {
        return taskList;
    }
    
    public Task getTaskAt(int i) throws PandaBotOutOfRangeException
    {
        try {
            return taskList.get(i);
        } catch (IndexOutOfBoundsException e) {
            throw new PandaBotOutOfRangeException();
        }
    }
    
    public int size() {
        return taskList.size();
    }
    
    public void deleteTask(int t) throws PandaBotOutOfRangeException {
        try {
            taskList.remove(t);
        } catch (IndexOutOfBoundsException e) {
            throw new PandaBotOutOfRangeException();
        }
    }
    
    public void addTask(Task t) {
        taskList.add(t);
    }
}
