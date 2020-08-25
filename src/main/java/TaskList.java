import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList;
    
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    
    public Task getTask(int index) {
        return this.taskList.get(index);
    }
    
    public void addTask(Task task) {
        this.taskList.add(task);
    }
    
    public void deleteTask(int index) {
        this.taskList.remove(index);
    }
    
    public int getSize() {
        return this.taskList.size();
    }
    
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
    
    @Override
    public String toString() {
        String string = "";
        for (int i = 0; i < getSize(); i++) {
            if (i == getSize() - 1) {
                string += ((i + 1) + ". " + getTask(i).toString());
            } else {
                string += ((i + 1) + ". " + getTask(i).toString() + "\n");
            }
        }
        return string;
    }
    
}
