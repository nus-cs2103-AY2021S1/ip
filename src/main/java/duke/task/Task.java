package duke.task;

public class Task {
    private String taskName;
    private boolean isCompleted;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markAsDone() {
        isCompleted = true;
    }

    public String[] taskToArray() {
        String done;
        if(isCompleted) {
            done = "0";
        } else {
            done = "1";
        }
        String[] str = new String[]{"Task", done, taskName};
        return str;
    }

    @Override
    public String toString () {
        if (isCompleted) {
            return "[✓] " + taskName;
        } else {
            return "[✗] " + taskName;
        }
    }
}
