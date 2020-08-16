import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    // sideffect: create & add task + return response
    protected String addEntry(String input) {
        Task newEntry = new Task(input);
        this.taskList.add(newEntry);
        return "added: " + input;
    }

    public ArrayList<Task> getAllTasks() {
        return taskList;
    }
}
