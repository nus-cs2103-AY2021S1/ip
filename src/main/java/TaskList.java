package main.java;
import java.util.List;
import java.util.ArrayList;

public class TaskList {
    List<Task> taskList = new ArrayList<>();

    public void addList(Task task) {
        taskList.add(task);
        System.out.println("added: " + task.getDescription());
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    @Override
    public String toString() {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            int taskNumber = i + 1;
            result = result + taskNumber + "." + taskList.get(i).toString() + "\n";
        }
        return result;
    }
}
