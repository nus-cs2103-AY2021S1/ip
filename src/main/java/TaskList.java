import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    // sideffect: create & add task + return response
    protected String addEntry(String input) {
        Task newEntry = new Task(input);
        this.taskList.add(newEntry);
        return "added: " + input;
    }

    // side effect: completes task + returns string for completed task
    protected String completeTask(int taskID) {
        taskList.set(taskID - 1, taskList.get(taskID - 1).complete());
        return taskList.get(taskID - 1).toString();
    }

    public ArrayList<Task> getAllTasks() {
        return taskList;
    }
}
