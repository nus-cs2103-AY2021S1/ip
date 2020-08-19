package Task;

public class Task {
    protected int position;
    protected String taskDescription;
    protected boolean taskCompleted;
    protected String date;

    public Task(int position, String taskDescription) {
        this.position = position;
        this.taskDescription = taskDescription;
        taskCompleted = false;
    }

    public void completeTask() {
        taskCompleted = true;
    }

    public String getTaskDescription() {
        return taskDescription;
    }
}
