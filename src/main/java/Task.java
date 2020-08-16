public class Task {
    private int position;
    private String taskDescription;
    private boolean taskCompleted;

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

    @Override
    public String toString() {
        if (!taskCompleted) {
            return position + ". [✗] " + taskDescription;
        } else {
            return position + ". [✓] " + taskDescription;
        }
    }
}
