import java.util.ArrayList;

public class Task {
    protected String taskTitle;
    protected boolean isDone;
    protected TaskTypes taskType;

    public Task(String taskTitle, Boolean isDone, TaskTypes taskType) {
        this.taskTitle = taskTitle;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatus() {
        return (isDone ? "✓" : "✗");
    }

    public String getStatusNum() {
        return (isDone ? "1" : "0");
    }

    public static void doneTask(int index, ArrayList<Task> tasks) {
        tasks.get(index - 1).markAsDone();
        Feedbacks.doneTaskMsg(index, tasks);
    }

    public static void deleteTask(int index, ArrayList<Task> tasks) {
        Task taskToDelete = tasks.get(index - 1);
        tasks.remove(index - 1);
        int newSizeOfTasks = tasks.size();
        Feedbacks.deleteTaskMsg(index, newSizeOfTasks, taskToDelete);
    }

    @Override
    public String toString() {
        return "[" + this.taskType + "]" + "[" + getStatus() + "] " + taskTitle;
    }

    public String writeToFile() {
        return this.taskType + " | " + getStatusNum() + " | " + taskTitle;
    }
}
