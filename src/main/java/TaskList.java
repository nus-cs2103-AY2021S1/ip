import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    ArrayList<Task> taskList = new ArrayList<>();

    private final static long serialVersionUID = 1234L;

    public void showList(){
        int counter = 1;
        for(Task task: taskList) {
            System.out.println(counter + ". " + task);
            counter++;
        }
    }
    public int getTaskLength() {
        return taskList.size();
    }

    public Task getTask(int index){
        return taskList.get(index-1);
    }

    public void removeTask(Task task) {
        taskList.remove(task);
    }

    public void addTask(Task task) {
        taskList.add(task);
    }
}
