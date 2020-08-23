package main.java;
import java.util.List;
import java.util.ArrayList;

public class TaskList {
    List<Task> taskList = new ArrayList<>();

    public void addTask(Task task) {
        taskList.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task.toString());
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
    }

    public Task getTask(int index) {
        return taskList.get(index);
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }
    public void deleteTask(int index) {
        Task taskToBeDeleted = taskList.get(index);
        taskList.remove(taskToBeDeleted);
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + taskToBeDeleted.toString());
        System.out.println("Now you have " + this.taskList.size() + " tasks in the list.");
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
