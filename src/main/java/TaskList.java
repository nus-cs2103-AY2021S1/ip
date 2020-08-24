import java.util.ArrayList;
import java.util.List;

public class TaskList {
    ArrayList<Task> taskList = new ArrayList<>();

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
