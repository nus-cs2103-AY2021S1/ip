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

    public void findTask(String display) {
        ArrayList<String> matches = new ArrayList<>();
        String task = display.substring(5);
        for (int i = 0; i < taskList.size(); i++) {
            String currentTask = taskList.get(i).toString();
            if (currentTask.contains(task)) {
                matches.add(currentTask);
            }
        }
        System.out.println("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matches.size(); i++) {
            System.out.println(matches.get(i) + "\n");
        }
    }
}
