package main.java;
import java.util.List;
import java.util.ArrayList;

public class TaskList {
    List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    private TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

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

    public TaskList findTaskWithKeyword(String keyword) {
        List<Task> taskWithKeyword = new ArrayList<>();
        for (Task task : taskList) {
            if (task.hasKeyword(keyword)) {
                taskWithKeyword.add(task);
            }
        }
        return new TaskList(taskWithKeyword);
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
