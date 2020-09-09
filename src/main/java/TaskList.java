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
        assert index > 0 : "index cannot be zero or negative";
        Task task = taskList.get(index);
        taskList.remove(index);
        return task;
    }
    
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public String findTask(String display) {
        assert display.length() > 0 : "input cannot be empty";
        ArrayList<String> matches = new ArrayList<>();
        String task = display.substring(5);
        for (Task value : taskList) {
            String currentTask = value.toString();
            if (currentTask.contains(task)) {
                matches.add(currentTask);
            }
        }
        StringBuilder reply = new StringBuilder("Here are the matching tasks in your list:\n");
        for (String match : matches) {
            reply.append(match).append("\n");
        }
        return reply.toString();
    }
}
