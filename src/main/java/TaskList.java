package main.java;
import java.util.List;
import java.util.ArrayList;

public class TaskList {
    List<Task> taskList = new ArrayList<>();

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public int getSize() {
        return taskList.size();
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    public void deleteTask(int index) {
        Task taskToBeDeleted = taskList.get(index);
        taskList.remove(taskToBeDeleted);
    }

    @Override
    public String toString() {
        String result = "Here are the tasks in your list:\n";
        for (int i = 0; i < taskList.size(); i++) {
            int taskNumber = i + 1;
            result = result + taskNumber + "." + taskList.get(i).toString()
                    + (i == taskList.size() - 1 ? "" : "\n");
        }
        return result;
    }
}
