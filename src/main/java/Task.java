public class Task {
    protected String taskDescription;
    protected boolean done;

    public Task(String taskDescription, boolean done) {
        this.taskDescription = taskDescription;
        this.done = done;
    }

    public String getTaskDescription() {
        return this.taskDescription;
    }

    public boolean getDone() {
        return this.done;
    }

    public void completeTask() {
        this.done = true;
    }

    @Override
    public String toString() {
        return (getDone() ? "[✓] " : "[✘] ") + getTaskDescription();
    }
}
