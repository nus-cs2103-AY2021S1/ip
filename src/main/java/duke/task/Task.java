package duke.task;

public class Task {
    private String taskName;
    private boolean completed;

    public Task(String taskName) {
        this.taskName = taskName;
        this.completed = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markAsDone() {
        completed = true;
    }

    public String[] taskToArray() {
        String done;
        if(completed) {
            done = "0";
        } else {
            done = "1";
        }
        String[] str = new String[]{"Task", done, taskName};
        return str;
    }

    @Override
    public String toString () {
        if (completed) {
            return "[✓] " + taskName;
        } else {
            return "[✗] " + taskName;
        }
    }
}
