import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> taskList = new ArrayList<>();

    public void showList(){
        for(Task task: taskList) {
            System.out.println(task);
        }
    }

    public void addTask(Task task) {
        taskList.add(task);
    }
}

//1. display the elements
// 2. add element
//3. remove element(later)
